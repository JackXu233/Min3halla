package cn.jacksigxu.min3halla.gui.menu;

import cn.jacksigxu.min3halla.block.entity.BTCFillingMachineBlockEntity;
import cn.jacksigxu.min3halla.init.MHItems;
import cn.jacksigxu.min3halla.init.MHMenuTypes;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class BTCFillinfMachineMenu extends AbstractContainerMenu {
    private final Container container;
    private final ContainerData containerData;
    protected final Level level;

    public static final int X_OFFSET = 0;
    public static final int Y_OFFSET = 0;

    public BTCFillinfMachineMenu(int id, Inventory inventory) {
        this(id, inventory, new SimpleContainer(4), new SimpleContainerData(BTCFillingMachineBlockEntity.MAX_DATA_COUNT));
    }

    public BTCFillinfMachineMenu(int id, Inventory inventory, Container container, ContainerData containerData) {
        super(MHMenuTypes.BTC_FILLING_MACHINE_MENU.get(), id);

        checkContainerSize(container, 4);
        checkContainerDataCount(containerData, BTCFillingMachineBlockEntity.MAX_DATA_COUNT);

        this.container = container;
        this.containerData = containerData;
        this.level = inventory.player.level();

        this.addSlot(new BTCFillinfMachineMenu.InputSlot(container, 0, 60, 55));
        this.addSlot(new BTCFillinfMachineMenu.InputSlot(container, 1, 140, 55));
        this.addSlot(new BTCFillinfMachineMenu.CanSlot(container, 2, 220, 55));
        this.addSlot(new BTCFillinfMachineMenu.ResultSlot(container, 3, 300, 55));

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
        ItemStack itemStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(pIndex);
        if (slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemStack = itemstack1.copy();
            if (pIndex == 3) {
                if (!this.moveItemStackTo(itemstack1, 4, 40, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickCraft(itemstack1, itemStack);
            } else if (pIndex <= 2) {
                if (!this.moveItemStackTo(itemstack1, 4, 40, true)) {
                    return ItemStack.EMPTY;
                }
            } else {
                if (!this.moveItemStackTo(itemstack1, 0, 2, false)) {
                    return ItemStack.EMPTY;
                } else if (pIndex < 31) {
                    if (!this.moveItemStackTo(itemstack1, 31, 40, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (pIndex < 40 && !this.moveItemStackTo(itemstack1, 4, 31, false)) {
                    return ItemStack.EMPTY;
                }
            }

            if (itemstack1.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (itemstack1.getCount() == itemStack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(pPlayer, itemstack1);
        }

        return itemStack;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return this.container.stillValid(pPlayer);
    }

    static class InputSlot extends Slot {
        public InputSlot(Container pContainer, int pSlot, int pX, int pY) {
            super(pContainer, pSlot, pX, pY);
        }

        @Override
        public boolean mayPlace(ItemStack pStack) {
            return true;
        }
    }

    static class CanSlot extends Slot {
        public CanSlot(Container pContainer, int pSlot, int pX, int pY) {
            super(pContainer, pSlot, pX, pY);
        }

        @Override
        public boolean mayPlace(ItemStack pStack) {
            return pStack.is(MHItems.BTC_CAN.get());
        }
    }

    static class ResultSlot extends Slot {
        public ResultSlot(Container pContainer, int pSlot, int pX, int pY) {
            super(pContainer, pSlot, pX, pY);
        }

        @Override
        public boolean mayPlace(ItemStack pStack) {
            return false;
        }
    }
}
