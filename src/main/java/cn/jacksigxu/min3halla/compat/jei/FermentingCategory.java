package cn.jacksigxu.min3halla.compat.jei;

import cn.jacksigxu.min3halla.MHMod;
import cn.jacksigxu.min3halla.init.MHItems;
import cn.jacksigxu.min3halla.recipe.FermentingRecipe;
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

public class FermentingCategory implements IRecipeCategory<FermentingRecipe> {

    public static final ResourceLocation TEXTURE = MHMod.loc("textures/gui/ferment_barrel.png");

    public static final RecipeType<FermentingRecipe> TYPE = new RecipeType<>(MHMod.loc("fermenting"), FermentingRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;

    public FermentingCategory(IGuiHelper helper) {
        this.background = helper.drawableBuilder(TEXTURE, 30, 22, 116, 38)
                .setTextureSize(256, 256)
                .build();

        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(MHItems.FERMENT_BARREL.get()));
    }

    @Override
    @ParametersAreNonnullByDefault
    public void draw(FermentingRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {

    }

    @Override
    public @NotNull RecipeType<FermentingRecipe> getRecipeType() {
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
    public void setRecipe(IRecipeLayoutBuilder builder, FermentingRecipe recipe, IFocusGroup group) {
        builder.addSlot(RecipeIngredientRole.INPUT, 10, 11).addIngredients(recipe.getInput());
        builder.addSlot(RecipeIngredientRole.OUTPUT, 86, 11).addItemStack(recipe.getResultItem(null));
    }
}
