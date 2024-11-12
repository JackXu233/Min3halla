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

    public static final int X_OFFSET = 0;
    public static final int Y_OFFSET = 0;

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

        this.addSlot(new DrinkMixerMenu.InputSlot(container, MHItems.SHAKER_POT.get(), 0, 46, 39));
        this.addSlot(new DrinkMixerMenu.InputSlot(container, MHItems.ADELHYDE.get(), 1, 21, 19));
        this.addSlot(new DrinkMixerMenu.InputSlot(container, MHItems.BRONSON_EXTRACT.get(), 2, 109, 39));
        this.addSlot(new DrinkMixerMenu.InputSlot(container, MHItems.POWDERED_DELTA.get(), 3, 130, 39));
        this.addSlot(new DrinkMixerMenu.InputSlot(container, MHItems.FLANERGIDE.get(), 4, 130, 39));
        this.addSlot(new DrinkMixerMenu.InputSlot(container, MHItems.KARMOTRINE.get(), 5, 130, 39));
        this.addSlot(new DrinkMixerMenu.InputSlot(container, Items.ICE, 6, 130, 39));
        this.addSlot(new DrinkMixerMenu.InputSlot(container, Items.REDSTONE, 7, 130, 39));
        this.addSlot(new DrinkMixerMenu.InputSlot(container, Tags.Items.DYES, 8, 130, 39));
        this.addSlot(new DrinkMixerMenu.InputSlot(container, 9, 130, 39));

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
        return null;
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return false;
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
