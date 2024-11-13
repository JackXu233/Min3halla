package cn.jacksigxu.min3halla.compat.jei;

import cn.jacksigxu.min3halla.MHMod;
import cn.jacksigxu.min3halla.init.MHItems;
import cn.jacksigxu.min3halla.recipe.MixingRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;

public class MixingCategory implements IRecipeCategory<MixingRecipe> {

    public static final ResourceLocation TEXTURE = MHMod.loc("textures/gui/drink_mixer_jei.png");

    public static final RecipeType<MixingRecipe> TYPE = new RecipeType<>(MHMod.loc("mixing"), MixingRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public MixingCategory(IGuiHelper helper) {
        this.background = helper.drawableBuilder(TEXTURE, 0, 0, 180, 100)
                .setTextureSize(256, 256)
                .build();

        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(MHItems.DRINK_MIXER.get()));
    }

    @Override
    @ParametersAreNonnullByDefault
    public void draw(MixingRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        int adeCount = recipe.ade;
        renderIngredients(guiGraphics, 8, 43, 0, 0, adeCount);

    }

    private void renderIngredients(GuiGraphics pGuiGraphics, int x, int y, int u, int v, int count) {
        if (count == -1) return;

        for (int k = 0; k < Math.min(5, count); k++) {
            pGuiGraphics.blit(TEXTURE, x + k * 5, y, u, v, 4, 9);
        }

        for (int k = 0; k < Math.min(5, count - 5); k++) {
            pGuiGraphics.blit(TEXTURE, x + k * 5, y + 11, u, v, 4, 9);
        }

        for (int k = 0; k < Math.min(5, count - 10); k++) {
            pGuiGraphics.blit(TEXTURE, x + k * 5, y, u, v + 9, 4, 9);
        }

        for (int k = 0; k < Math.min(5, count - 15); k++) {
            pGuiGraphics.blit(TEXTURE, x + k * 5, y + 11, u, v + 9, 4, 9);
        }
    }

    @Override
    public @NotNull RecipeType<MixingRecipe> getRecipeType() {
        return TYPE;
    }

    @Override
    public @NotNull Component getTitle() {
        return Component.translatable("block.min3halla.drink_mixer");
    }

    @Override
    public @NotNull IDrawable getBackground() {
        return background;
    }

    @Override
    public @NotNull IDrawable getIcon() {
        return icon;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void setRecipe(IRecipeLayoutBuilder builder, MixingRecipe recipe, IFocusGroup group) {
        if (recipe.ade != 0) {
            builder.addSlot(RecipeIngredientRole.INPUT, 102, 19).addItemStack(new ItemStack(MHItems.ADELHYDE.get()));
        }


//        builder.addSlot(RecipeIngredientRole.INPUT, 43, 19).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 102, 19).addItemStack(recipe.getResultItem(null));
    }
}
