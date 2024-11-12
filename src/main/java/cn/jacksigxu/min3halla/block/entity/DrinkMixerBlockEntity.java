package cn.jacksigxu.min3halla.block.entity;

import cn.jacksigxu.min3halla.init.MHBlockEntityTypes;
import cn.jacksigxu.min3halla.recipe.MixingRecipe;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class DrinkMixerBlockEntity extends BlockEntity implements MenuProvider {

    public DrinkMixerBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(MHBlockEntityTypes.DRINK_MIXER_BLOCK_ENTITY.get(), pPos, pBlockState);
    }

    private Optional<MixingRecipe> getCurrentRecipe() {
        if (this.level == null) {
            return Optional.empty();
        }

        SimpleContainer inventory = new SimpleContainer(9);

        return this.level.getRecipeManager().getRecipeFor(MixingRecipe.Type.INSTANCE, inventory, level);
    }

    private boolean hasRecipe() {
        Optional<MixingRecipe> recipe = getCurrentRecipe();

        if (recipe.isEmpty()) {
            return false;
        }

        if (getLevel() == null) {
            return false;
        }

        ItemStack result = recipe.get().getResultItem(getLevel().registryAccess());

        // TODO 完成判定
        return true;
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("menu.min3halla.drink_mixer");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return null;
    }
}
