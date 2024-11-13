package cn.jacksigxu.min3halla.block.entity;

import cn.jacksigxu.min3halla.gui.menu.FermentBarrelMenu;
import cn.jacksigxu.min3halla.init.MHBlockEntityTypes;
import cn.jacksigxu.min3halla.recipe.FermentingRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.wrapper.SidedInvWrapper;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class FermentBarrelBlockEntity extends BlockEntity implements WorldlyContainer, MenuProvider {

    protected static final int SLOT_INPUT = 0;
    protected static final int SLOT_OUTPUT = 1;

    public static final int MAX_PROGRESS = 1800;
    public static final int MAX_DATA_COUNT = 1;

    protected NonNullList<ItemStack> items = NonNullList.withSize(2, ItemStack.EMPTY);

    private int progress = 0;

    protected final ContainerData dataAccess = new ContainerData() {
        public int get(int pIndex) {
            return FermentBarrelBlockEntity.this.progress;
        }

        public void set(int pIndex, int pValue) {
            FermentBarrelBlockEntity.this.progress = pValue;
        }

        public int getCount() {
            return MAX_DATA_COUNT;
        }
    };

    private LazyOptional<?>[] itemHandlers = SidedInvWrapper.create(this, Direction.UP, Direction.DOWN, Direction.NORTH);

    public FermentBarrelBlockEntity(BlockPos pPos, BlockState pState) {
        super(MHBlockEntityTypes.FERMENT_BARREL_BLOCK_ENTITY.get(), pPos, pState);
    }

    public static void serverTick(Level pLevel, BlockPos pPos, BlockState pState, FermentBarrelBlockEntity blockEntity) {
        if (blockEntity.hasRecipe()) {
            blockEntity.progress++;
            if (blockEntity.progress >= MAX_PROGRESS) {
                blockEntity.resetProgress();
                blockEntity.craftItem();
            }
        } else {
            blockEntity.resetProgress();
        }
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(pTag, this.items);
        this.progress = pTag.getInt("Progress");
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        ContainerHelper.saveAllItems(pTag, this.items);
        pTag.putInt("Progress", this.progress);
    }

    @Override
    public int[] getSlotsForFace(Direction pSide) {
        if (pSide == Direction.DOWN) {
            return new int[]{SLOT_OUTPUT};
        } else {
            return new int[]{SLOT_INPUT};
        }
    }

    @Override
    public boolean canPlaceItemThroughFace(int pIndex, ItemStack pItemStack, @Nullable Direction pDirection) {
        return pIndex == SLOT_INPUT && pDirection != Direction.DOWN;
    }

    @Override
    public boolean canTakeItemThroughFace(int pIndex, ItemStack pStack, Direction pDirection) {
        return pIndex == SLOT_OUTPUT && pDirection != Direction.UP;
    }

    @Override
    public int getContainerSize() {
        return this.items.size();
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack itemstack : this.items) {
            if (!itemstack.isEmpty()) {
                return false;
            }
        }

        return true;
    }

    @Override
    public ItemStack getItem(int pSlot) {
        return this.items.get(pSlot);
    }

    @Override
    public ItemStack removeItem(int pSlot, int pAmount) {
        return ContainerHelper.removeItem(this.items, pSlot, pAmount);
    }

    @Override
    public ItemStack removeItemNoUpdate(int pSlot) {
        return ContainerHelper.takeItem(this.items, pSlot);
    }

    @Override
    public void setItem(int pSlot, ItemStack pStack) {
        ItemStack itemstack = this.items.get(pSlot);
        boolean flag = !pStack.isEmpty() && ItemStack.isSameItemSameTags(itemstack, pStack);
        this.items.set(pSlot, pStack);
        if (pStack.getCount() > this.getMaxStackSize()) {
            pStack.setCount(this.getMaxStackSize());
        }

        if (pSlot == 0 && !flag) {
            this.setChanged();
        }
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return Container.stillValidBlockEntity(this, pPlayer);
    }

    @Override
    public void clearContent() {
        this.items.clear();
    }

    public void craftItem() {
        Optional<FermentingRecipe> recipe = getCurrentRecipe();
        if (recipe.isEmpty()) {
            return;
        }
        ItemStack result = recipe.get().getResultItem(null);

        ItemStack input = this.items.get(SLOT_INPUT);
        input.shrink(1);

        ItemStack output = this.items.get(SLOT_OUTPUT);
        this.items.set(SLOT_OUTPUT, new ItemStack(result.getItem(), output.getCount() + result.getCount()));

        this.setChanged();
    }

    private Optional<FermentingRecipe> getCurrentRecipe() {
        if (this.level == null) {
            return Optional.empty();
        }
        SimpleContainer inventory = new SimpleContainer(this.items.size());
        for (int i = 0; i < this.items.size(); i++) {
            inventory.setItem(i, this.items.get(i));
        }
        return this.level.getRecipeManager().getRecipeFor(FermentingRecipe.Type.INSTANCE, inventory, level);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("menu.min3halla.ferment_barrel");
    }

    private boolean hasRecipe() {
        Optional<FermentingRecipe> recipe = getCurrentRecipe();

        if (recipe.isEmpty()) {
            return false;
        }

        if (getLevel() == null) {
            return false;
        }

        ItemStack result = recipe.get().getResultItem(getLevel().registryAccess());

        return canInsertAmountIntoOutputSlot(result.getCount()) && canInsertItemIntoOutputSlot(result.getItem());
    }

    private boolean canInsertItemIntoOutputSlot(Item item) {
        return this.items.get(SLOT_OUTPUT).isEmpty() || this.items.get(SLOT_OUTPUT).is(item);
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        return this.items.get(SLOT_OUTPUT).getCount() + count <= this.items.get(SLOT_OUTPUT).getMaxStackSize();
    }

    private void resetProgress() {
        this.progress = 0;
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new FermentBarrelMenu(pContainerId, pPlayerInventory, this, this.dataAccess);
    }

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, @Nullable Direction side) {
        if (!this.remove && side != null && cap == ForgeCapabilities.ITEM_HANDLER) {
            if (side == Direction.UP) {
                return itemHandlers[0].cast();
            } else if (side == Direction.DOWN) {
                return itemHandlers[1].cast();
            } else {
                return itemHandlers[2].cast();
            }
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        for (LazyOptional<?> itemHandler : itemHandlers) itemHandler.invalidate();
    }

    @Override
    public void reviveCaps() {
        super.reviveCaps();
        this.itemHandlers = SidedInvWrapper.create(this, Direction.UP, Direction.DOWN, Direction.NORTH);
    }
}
