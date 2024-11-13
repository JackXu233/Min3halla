package cn.jacksigxu.min3halla.block.entity;

import cn.jacksigxu.min3halla.gui.menu.DrinkMixerMenu;
import cn.jacksigxu.min3halla.init.MHBlockEntityTypes;
import cn.jacksigxu.min3halla.init.MHItems;
import cn.jacksigxu.min3halla.recipe.MixingRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.*;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.wrapper.SidedInvWrapper;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class DrinkMixerBlockEntity extends BlockEntity implements WorldlyContainer, MenuProvider {

    protected static final int SLOT_SHAKER_POT = 0;
    protected static final int SLOT_ADELHYDE = 1;
    protected static final int SLOT_BRONSON_EXTRACT = 2;
    protected static final int SLOT_POWDERED_DELTA = 3;
    protected static final int SLOT_FLANERGIDE = 4;
    protected static final int SLOT_KARMOTRINE = 5;
    protected static final int SLOT_ICE = 6;
    protected static final int SLOT_AGED = 7;
    protected static final int SLOT_DYE = 8;
    protected static final int SLOT_EXTRA = 9;

    private static final int[] SLOTS_FOR_UP = new int[]{0};
    private static final int[] SLOTS_FOR_SIDES = new int[]{1, 2, 3, 4, 5, 6, 7};
    private static final int[] SLOTS_FOR_DOWN = new int[]{0};

    public static final int MAX_DATA_COUNT = 10;

    protected NonNullList<ItemStack> items = NonNullList.withSize(10, ItemStack.EMPTY);

    private int adeCount = 0;
    private int bexCount = 0;
    private int pwdCount = 0;
    private int flaCount = 0;
    private int karCount = 0;
    private boolean useIce = false;
    private boolean useAged = false;
    private boolean useDye = false;
    private boolean useExtra = false;
    private boolean canCraft = false;

    private LazyOptional<?>[] itemHandlers = SidedInvWrapper.create(this, Direction.UP, Direction.DOWN, Direction.NORTH);

    protected final ContainerData dataAccess = new ContainerData() {
        public int get(int pIndex) {
            return switch (pIndex) {
                case 0 -> DrinkMixerBlockEntity.this.adeCount;
                case 1 -> DrinkMixerBlockEntity.this.bexCount;
                case 2 -> DrinkMixerBlockEntity.this.pwdCount;
                case 3 -> DrinkMixerBlockEntity.this.flaCount;
                case 4 -> DrinkMixerBlockEntity.this.karCount;
                case 5 -> DrinkMixerBlockEntity.this.useIce ? 1 : 0;
                case 6 -> DrinkMixerBlockEntity.this.useAged ? 1 : 0;
                case 7 -> DrinkMixerBlockEntity.this.useDye ? 1 : 0;
                case 8 -> DrinkMixerBlockEntity.this.useExtra ? 1 : 0;
                case 9 -> DrinkMixerBlockEntity.this.canCraft ? 1 : 0;
                default -> 0;
            };
        }

        public void set(int pIndex, int pValue) {
            switch (pIndex) {
                case 0:
                    DrinkMixerBlockEntity.this.adeCount = pValue;
                    break;
                case 1:
                    DrinkMixerBlockEntity.this.bexCount = pValue;
                    break;
                case 2:
                    DrinkMixerBlockEntity.this.pwdCount = pValue;
                    break;
                case 3:
                    DrinkMixerBlockEntity.this.flaCount = pValue;
                    break;
                case 4:
                    DrinkMixerBlockEntity.this.karCount = pValue;
                    break;
                case 5:
                    DrinkMixerBlockEntity.this.useIce = pValue == 1;
                    break;
                case 6:
                    DrinkMixerBlockEntity.this.useAged = pValue == 1;
                    break;
                case 7:
                    DrinkMixerBlockEntity.this.useDye = pValue == 1;
                    break;
                case 8:
                    DrinkMixerBlockEntity.this.useExtra = pValue == 1;
                    break;
                case 9:
                    DrinkMixerBlockEntity.this.canCraft = pValue == 1;
                    break;
            }
        }

        public int getCount() {
            return MAX_DATA_COUNT;
        }
    };

    public DrinkMixerBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(MHBlockEntityTypes.DRINK_MIXER_BLOCK_ENTITY.get(), pPos, pBlockState);
    }

    public static void serverTick(Level pLevel, BlockPos pPos, BlockState pState, DrinkMixerBlockEntity blockEntity) {
        // 雪克壶槽位为空时，重置全部数据
        ItemStack shaker = blockEntity.items.get(SLOT_SHAKER_POT);
        if (shaker.isEmpty() || !shaker.is(MHItems.SHAKER_POT.get())) {
            boolean changed = false;
            if (blockEntity.adeCount != 0) {
                blockEntity.adeCount = 0;
                changed = true;
            }
            if (blockEntity.bexCount != 0) {
                blockEntity.bexCount = 0;
                changed = true;
            }
            if (blockEntity.pwdCount != 0) {
                blockEntity.pwdCount = 0;
                changed = true;
            }
            if (blockEntity.flaCount != 0) {
                blockEntity.flaCount = 0;
                changed = true;
            }
            if (blockEntity.karCount != 0) {
                blockEntity.karCount = 0;
                changed = true;
            }
            if (blockEntity.useIce) {
                blockEntity.useIce = false;
                changed = true;
            }
            if (blockEntity.useAged) {
                blockEntity.useAged = false;
                changed = true;
            }
            if (blockEntity.useDye) {
                blockEntity.useDye = false;
                changed = true;
            }
            if (blockEntity.useExtra) {
                blockEntity.useExtra = false;
                changed = true;
            }
            if (blockEntity.canCraft) {
                blockEntity.canCraft = false;
                changed = true;
            }
            if (changed) {
                blockEntity.setChanged();
            }
        } else {
            if (!blockEntity.canCraft) {
                blockEntity.canCraft = true;
                blockEntity.setChanged();
            }
        }
        if (blockEntity.items.get(SLOT_ADELHYDE).isEmpty() && blockEntity.adeCount != 0) {
            blockEntity.adeCount = 0;
            blockEntity.setChanged();
        }
        if (blockEntity.items.get(SLOT_BRONSON_EXTRACT).isEmpty() && blockEntity.bexCount != 0) {
            blockEntity.bexCount = 0;
            blockEntity.setChanged();
        }
        if (blockEntity.items.get(SLOT_POWDERED_DELTA).isEmpty() && blockEntity.pwdCount != 0) {
            blockEntity.pwdCount = 0;
            blockEntity.setChanged();
        }
        if (blockEntity.items.get(SLOT_FLANERGIDE).isEmpty() && blockEntity.flaCount != 0) {
            blockEntity.flaCount = 0;
            blockEntity.setChanged();
        }
        if (blockEntity.items.get(SLOT_KARMOTRINE).isEmpty() && blockEntity.karCount != 0) {
            blockEntity.karCount = 0;
            blockEntity.setChanged();
        }
        if (blockEntity.items.get(SLOT_ICE).isEmpty() && blockEntity.useIce) {
            blockEntity.useIce = false;
            blockEntity.setChanged();
        }
        if (blockEntity.items.get(SLOT_AGED).isEmpty() && blockEntity.useAged) {
            blockEntity.useAged = false;
            blockEntity.setChanged();
        }
        if (blockEntity.items.get(SLOT_DYE).isEmpty() && blockEntity.useDye) {
            blockEntity.useDye = false;
            blockEntity.setChanged();
        }
        if (blockEntity.items.get(SLOT_EXTRA).isEmpty() && blockEntity.useExtra) {
            blockEntity.useExtra = false;
            blockEntity.setChanged();
        }
    }

    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        this.items = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(pTag, this.items);

        this.adeCount = pTag.getInt("Ade");
        this.bexCount = pTag.getInt("Bex");
        this.pwdCount = pTag.getInt("Pwd");
        this.flaCount = pTag.getInt("Fla");
        this.karCount = pTag.getInt("Kar");

        this.useIce = pTag.getBoolean("Ice");
        this.useAged = pTag.getBoolean("Aged");
        this.useDye = pTag.getBoolean("Dye");
        this.useExtra = pTag.getBoolean("Extra");
    }

    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        ContainerHelper.saveAllItems(pTag, this.items);

        pTag.putInt("Ade", this.adeCount);
        pTag.putInt("Bex", this.bexCount);
        pTag.putInt("Pwd", this.pwdCount);
        pTag.putInt("Fla", this.flaCount);
        pTag.putInt("Kar", this.karCount);

        pTag.putBoolean("Ice", this.useIce);
        pTag.putBoolean("Aged", this.useAged);
        pTag.putBoolean("Dye", this.useDye);
        pTag.putBoolean("Extra", this.useExtra);
    }

    @Override
    public int[] getSlotsForFace(Direction pSide) {
        if (pSide == Direction.UP) {
            return SLOTS_FOR_UP;
        } else if (pSide == Direction.DOWN) {
            return SLOTS_FOR_DOWN;
        } else {
            return SLOTS_FOR_SIDES;
        }
    }

    @Override
    public boolean canPlaceItemThroughFace(int pIndex, ItemStack pItemStack, @Nullable Direction pDirection) {
        if (pDirection == Direction.UP) {
            return pIndex == SLOT_SHAKER_POT && pItemStack.is(MHItems.SHAKER_POT.get());
        } else if (pDirection == Direction.DOWN) {
            return false;
        } else {
            return switch (pIndex) {
                case SLOT_ADELHYDE -> pItemStack.is(MHItems.ADELHYDE.get());
                case SLOT_BRONSON_EXTRACT -> pItemStack.is(MHItems.BRONSON_EXTRACT.get());
                case SLOT_POWDERED_DELTA -> pItemStack.is(MHItems.POWDERED_DELTA.get());
                case SLOT_FLANERGIDE -> pItemStack.is(MHItems.FLANERGIDE.get());
                case SLOT_KARMOTRINE -> pItemStack.is(MHItems.KARMOTRINE.get());
                case SLOT_ICE -> pItemStack.is(Items.ICE);
                case SLOT_AGED -> pItemStack.is(Items.REDSTONE);
                default -> false;
            };
        }
    }

    @Override
    public boolean canTakeItemThroughFace(int pIndex, ItemStack pStack, Direction pDirection) {
        return pDirection == Direction.DOWN && pIndex == SLOT_SHAKER_POT && pStack.is(MHItems.SHAKER_POT.get())
                && pStack.getTag() != null && pStack.getTag().contains("Result");
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
        if (this.level == null) return;

        Optional<MixingRecipe> currentRecipe = getCurrentRecipe();
        if (currentRecipe.isEmpty()) {
            if (canShakerPotInsertItem()) {
                consumeIngredients();
                makeFailOutput();
                resetData();
            }
            return;
        }
        MixingRecipe recipe = currentRecipe.get();

        ItemStack result = recipe.getResultItem(null);
        ItemStack stack = this.items.get(SLOT_SHAKER_POT);

        consumeIngredients();
        if (result == null) {
            makeFailOutput();
        } else if (canShakerPotInsertItem()) {
            stack.getOrCreateTag().put("Result", result.serializeNBT());
            stack.getOrCreateTag().putBoolean("Blend", recipe.blend);
            stack.getOrCreateTag().putInt("Alcohol", this.karCount);
        } else {
            makeFailOutput();
        }

        resetData();
    }

    private void consumeIngredients() {
        if (!this.items.get(SLOT_ADELHYDE).isEmpty()) {
            this.items.get(SLOT_ADELHYDE).shrink(this.adeCount);
        }
        if (!this.items.get(SLOT_BRONSON_EXTRACT).isEmpty()) {
            this.items.get(SLOT_BRONSON_EXTRACT).shrink(this.bexCount);
        }
        if (!this.items.get(SLOT_POWDERED_DELTA).isEmpty()) {
            this.items.get(SLOT_POWDERED_DELTA).shrink(this.pwdCount);
        }
        if (!this.items.get(SLOT_FLANERGIDE).isEmpty()) {
            this.items.get(SLOT_FLANERGIDE).shrink(this.flaCount);
        }
        if (!this.items.get(SLOT_KARMOTRINE).isEmpty()) {
            this.items.get(SLOT_KARMOTRINE).shrink(this.karCount);
        }
        if (this.useIce) {
            if (!this.items.get(SLOT_ICE).isEmpty()) {
                this.items.get(SLOT_ICE).shrink(1);
            }
        }
        if (this.useAged) {
            if (!this.items.get(SLOT_AGED).isEmpty()) {
                this.items.get(SLOT_AGED).shrink(1);
            }
        }
        if (this.useDye) {
            if (!this.items.get(SLOT_DYE).isEmpty()) {
                this.items.get(SLOT_DYE).shrink(1);
            }
        }
        if (this.useExtra) {
            if (!this.items.get(SLOT_EXTRA).isEmpty()) {
                this.items.get(SLOT_EXTRA).shrink(1);
            }
        }

        this.setChanged();
    }

    private void makeFailOutput() {
        ItemStack stack = this.items.get(SLOT_SHAKER_POT);
        if (stack.isEmpty() || !stack.is(MHItems.SHAKER_POT.get())) return;

        ItemStack result = new ItemStack(MHItems.ERROR_DRINK.get());
        stack.getOrCreateTag().put("Result", result.serializeNBT());
        stack.getOrCreateTag().putBoolean("Blend", false);
        stack.getOrCreateTag().putInt("Alcohol", 20);
    }

    private void resetData() {
        this.adeCount = 0;
        this.bexCount = 0;
        this.pwdCount = 0;
        this.flaCount = 0;
        this.karCount = 0;
        this.useIce = false;
        this.useAged = false;
        this.useDye = false;
        this.useExtra = false;
        this.setChanged();
    }

    private Optional<MixingRecipe> getCurrentRecipe() {
        if (this.level == null) {
            return Optional.empty();
        }

        SimpleContainer inventory = new SimpleContainer(9);
        inventory.setItem(0, new ItemStack(MHItems.ADELHYDE.get(), this.adeCount));
        inventory.setItem(1, new ItemStack(MHItems.BRONSON_EXTRACT.get(), this.bexCount));
        inventory.setItem(2, new ItemStack(MHItems.POWDERED_DELTA.get(), this.pwdCount));
        inventory.setItem(3, new ItemStack(MHItems.FLANERGIDE.get(), this.flaCount));
        inventory.setItem(4, new ItemStack(MHItems.KARMOTRINE.get(), this.karCount));
        if (this.useIce) {
            inventory.setItem(5, this.items.get(5));
        }
        if (this.useAged) {
            inventory.setItem(6, this.items.get(6));
        }
        if (this.useDye) {
            inventory.setItem(7, this.items.get(7));
        }
        if (this.useExtra) {
            inventory.setItem(8, this.items.get(8));
        }

        return this.level.getRecipeManager().getRecipeFor(MixingRecipe.Type.INSTANCE, inventory, level);
    }

    private boolean canShakerPotInsertItem() {
        ItemStack stack = this.items.get(SLOT_SHAKER_POT);
        if (stack.isEmpty()) return false;
        if (!stack.is(MHItems.SHAKER_POT.get())) return false;

        return stack.getTag() == null || !stack.getTag().contains("Result");
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("menu.min3halla.drink_mixer");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        if (this.getLevel() != null) {
            return new DrinkMixerMenu(pContainerId, pPlayerInventory, this, this.dataAccess, ContainerLevelAccess.create(this.getLevel(), this.getBlockPos()));
        }
        return null;
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag pTag = new CompoundTag();
        ContainerHelper.saveAllItems(pTag, this.items, true);

        pTag.putInt("Ade", this.adeCount);
        pTag.putInt("Bex", this.bexCount);
        pTag.putInt("Pwd", this.pwdCount);
        pTag.putInt("Fla", this.flaCount);
        pTag.putInt("Kar", this.karCount);

        pTag.putBoolean("Ice", this.useIce);
        pTag.putBoolean("Aged", this.useAged);
        pTag.putBoolean("Dye", this.useDye);
        pTag.putBoolean("Extra", this.useExtra);
        return pTag;
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
