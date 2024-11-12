package cn.jacksigxu.min3halla.gui.menu;

import cn.jacksigxu.min3halla.block.entity.DrinkMixerBlockEntity;
import cn.jacksigxu.min3halla.init.MHItems;
import cn.jacksigxu.min3halla.init.MHMenuTypes;
import net.minecraft.tags.TagKey;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.Tags;

public class DrinkMixerMenu extends AbstractContainerMenu {

    private final Container container;
    private final ContainerData containerData;
    protected final Level level;

    public static final int X_OFFSET = 34;
    public static final int Y_OFFSET = 37;

    public DrinkMixerMenu(int id, Inventory inventory) {
        this(id, inventory, new SimpleContainer(10), new SimpleContainerData(DrinkMixerBlockEntity.MAX_DATA_COUNT));
    }

    public DrinkMixerMenu(int id, Inventory inventory, Container container, ContainerData containerData) {
        super(MHMenuTypes.DRINK_MIXER_MENU.get(), id);

        checkContainerSize(container, 10);
        checkContainerDataCount(containerData, DrinkMixerBlockEntity.MAX_DATA_COUNT);

        this.container = container;
        this.containerData = containerData;
        this.level = inventory.player.level();

        this.addSlot(new DrinkMixerMenu.InputSlot(container, MHItems.SHAKER_POT.get(), 0, 114, 59));
        this.addSlot(new DrinkMixerMenu.InputSlot(container, MHItems.ADELHYDE.get(), 1, 39, 21));
        this.addSlot(new DrinkMixerMenu.InputSlot(container, MHItems.BRONSON_EXTRACT.get(), 2, 99, 21));
        this.addSlot(new DrinkMixerMenu.InputSlot(container, MHItems.POWDERED_DELTA.get(), 3, 159, 21));
        this.addSlot(new DrinkMixerMenu.InputSlot(container, MHItems.FLANERGIDE.get(), 4, 39, 59));
        this.addSlot(new DrinkMixerMenu.InputSlot(container, MHItems.KARMOTRINE.get(), 5, 159, 59));
        this.addSlot(new DrinkMixerMenu.InputSlot(container, Items.ICE, 6, 11, 22));
        this.addSlot(new DrinkMixerMenu.InputSlot(container, Items.REDSTONE, 7, 11, 60));
        this.addSlot(new DrinkMixerMenu.InputSlot(container, Tags.Items.DYES, 8, 217, 22));
        this.addSlot(new DrinkMixerMenu.InputSlot(container, 9, 217, 60));

        this.addDataSlots(containerData);

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(inventory, j + i * 9 + 9, 8 + j * 18 + X_OFFSET, 84 + i * 18 + Y_OFFSET));
            }
        }

        for (int k = 0; k < 9; ++k) {
            this.addSlot(new Slot(inventory, k, 8 + k * 18 + X_OFFSET, 142 + Y_OFFSET));
        }
    }

    @Override
    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(pIndex);
        if (slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (pIndex == 0) {
                if (!this.moveItemStackTo(itemstack1, 10, 46, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickCraft(itemstack1, itemstack);
            } else if (pIndex <= 9) {
                if (!this.moveItemStackTo(itemstack1, 10, 46, true)) {
                    return ItemStack.EMPTY;
                }
            } else if (pIndex <= 45) {
                if (itemstack1.is(MHItems.SHAKER_POT.get())) {
                    if (!this.moveItemStackTo(itemstack1, 0, 1, true)) {
                        return ItemStack.EMPTY;
                    }
                } else if (itemstack1.is(MHItems.ADELHYDE.get())) {
                    if (!this.moveItemStackTo(itemstack1, 1, 2, true)) {
                        return ItemStack.EMPTY;
                    }
                } else if (itemstack1.is(MHItems.BRONSON_EXTRACT.get())) {
                    if (!this.moveItemStackTo(itemstack1, 2, 3, true)) {
                        return ItemStack.EMPTY;
                    }
                } else if (itemstack1.is(MHItems.POWDERED_DELTA.get())) {
                    if (!this.moveItemStackTo(itemstack1, 3, 4, true)) {
                        return ItemStack.EMPTY;
                    }
                } else if (itemstack1.is(MHItems.FLANERGIDE.get())) {
                    if (!this.moveItemStackTo(itemstack1, 4, 5, true)) {
                        return ItemStack.EMPTY;
                    }
                } else if (itemstack1.is(MHItems.KARMOTRINE.get())) {
                    if (!this.moveItemStackTo(itemstack1, 5, 6, true)) {
                        return ItemStack.EMPTY;
                    }
                } else if (itemstack1.is(Items.ICE)) {
                    if (!this.moveItemStackTo(itemstack1, 6, 7, true)) {
                        return ItemStack.EMPTY;
                    }
                } else if (itemstack1.is(Items.REDSTONE)) {
                    if (!this.moveItemStackTo(itemstack1, 7, 8, true)) {
                        return ItemStack.EMPTY;
                    }
                } else if (itemstack1.is(Tags.Items.DYES)) {
                    if (!this.moveItemStackTo(itemstack1, 8, 9, true)) {
                        return ItemStack.EMPTY;
                    }
                } else if (!this.moveItemStackTo(itemstack1, 9, 10, true)) {
                    return ItemStack.EMPTY;
                } else if (pIndex >= 37 && !this.moveItemStackTo(itemstack1, 10, 37, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemstack1, 10, 46, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(pPlayer, itemstack1);
        }

        return itemstack;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return this.container.stillValid(pPlayer);
    }

    static class InputSlot extends Slot {

        private Item mayPlaceItem = null;
        private TagKey<Item> itemTagKey = null;

        public InputSlot(Container pContainer, int pSlot, int pX, int pY) {
            super(pContainer, pSlot, pX, pY);
        }

        public InputSlot(Container pContainer, Item mayPlaceItem, int pSlot, int pX, int pY) {
            super(pContainer, pSlot, pX, pY);
            this.mayPlaceItem = mayPlaceItem;
        }

        public InputSlot(Container pContainer, TagKey<Item> itemTagKey, int pSlot, int pX, int pY) {
            super(pContainer, pSlot, pX, pY);
            this.itemTagKey = itemTagKey;
        }

        public boolean mayPlace(ItemStack pStack) {
            return (mayPlaceItem != null && pStack.is(mayPlaceItem))
                    || (itemTagKey != null && pStack.is(itemTagKey))
                    || (mayPlaceItem == null && itemTagKey == null);
        }

        public int getMaxStackSize() {
            return 64;
        }
    }

}
