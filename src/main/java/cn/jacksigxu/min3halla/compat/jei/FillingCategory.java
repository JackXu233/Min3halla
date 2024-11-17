package cn.jacksigxu.min3halla.compat.jei;

import cn.jacksigxu.min3halla.MHMod;
import cn.jacksigxu.min3halla.init.MHItems;
import cn.jacksigxu.min3halla.recipe.FillingRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
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

public class FillingCategory implements IRecipeCategory<FillingRecipe> {

    public static final ResourceLocation TEXTURE = MHMod.loc("textures/gui/btc_filling_machine.png");

    public static final RecipeType<FillingRecipe> TYPE = new RecipeType<>(MHMod.loc("filling"), FillingRecipe.class);

    private final IDrawable background;
    private final IDrawable icon;
    private final IDrawableAnimated arrow;

    public FillingCategory(IGuiHelper helper) {
        this.background = helper.drawableBuilder(TEXTURE, 20, 8, 134, 67)
                .setTextureSize(256, 256)
                .build();

        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(MHItems.FERMENT_BARREL.get()));

        this.arrow = helper.drawableBuilder(TEXTURE, 177, 0, 22, 16)
                .setTextureSize(256, 256)
                .buildAnimated(100, IDrawableAnimated.StartDirection.LEFT, false);
    }

    @Override
    @ParametersAreNonnullByDefault
    public void draw(FillingRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        this.arrow.draw(guiGraphics, 64, 22);
    }

    @Override
    public @NotNull RecipeType<FillingRecipe> getRecipeType() {
        return TYPE;
    }

    @Override
    public @NotNull Component getTitle() {
        return Component.translatable("block.min3halla.btc_filling_machine");
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
    public void setRecipe(IRecipeLayoutBuilder builder, FillingRecipe recipe, IFocusGroup group) {
        var ingredients = recipe.getIngredients();
        if (!ingredients.isEmpty()) {
            builder.addSlot(RecipeIngredientRole.INPUT, 8, 22).addIngredients(ingredients.get(0));
        }
        if (ingredients.size() > 1) {
            builder.addSlot(RecipeIngredientRole.INPUT, 36, 22).addIngredients(ingredients.get(1));
        }
        builder.addSlot(RecipeIngredientRole.INPUT, 104, 4).addItemStack(new ItemStack(MHItems.BTC_CAN.get()));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 104, 43).addItemStack(recipe.getResultItem(null));
    }
}
