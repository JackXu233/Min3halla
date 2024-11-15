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
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
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
        renderIngredients(guiGraphics, 26, 13, 0, 119, recipe.ade);
        renderIngredients(guiGraphics, 86, 13, 5, 126, recipe.bex);
        renderIngredients(guiGraphics, 146, 13, 10, 133, recipe.pwd);
        renderIngredients(guiGraphics, 26, 51, 15, 140, recipe.fla);
        renderIngredients(guiGraphics, 146, 51, 20, 147, recipe.kar);
    }

    private void renderIngredients(GuiGraphics pGuiGraphics, int x, int y, int u, int v, int count) {
        if (count == -1) {
            pGuiGraphics.blit(TEXTURE, x + 5, y + 7, 0, v, 14, 7);
            return;
        }

        for (int k = 0; k < Math.min(5, count); k++) {
            pGuiGraphics.blit(TEXTURE, x + k * 5, y, u, 101, 4, 9);
        }

        for (int k = 0; k < Math.min(5, count - 5); k++) {
            pGuiGraphics.blit(TEXTURE, x + k * 5, y + 11, u, 101, 4, 9);
        }

        for (int k = 0; k < Math.min(5, count - 10); k++) {
            pGuiGraphics.blit(TEXTURE, x + k * 5, y, u, 110, 4, 9);
        }

        for (int k = 0; k < Math.min(5, count - 15); k++) {
            pGuiGraphics.blit(TEXTURE, x + k * 5, y + 11, u, 110, 4, 9);
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
        int adeCount = recipe.ade;
        if (adeCount != 0) {
            if (adeCount < 0) {
                adeCount = 1;
            }
            builder.addSlot(RecipeIngredientRole.INPUT, 7, 15).addItemStack(new ItemStack(MHItems.ADELHYDE.get(), adeCount))
                    .addTooltipCallback((tooltipContext, tooltip) -> {
                        if (recipe.ade == -1) {
                            tooltip.add(Component.translatable("des.min3halla.recipe.optional").withStyle(ChatFormatting.GOLD));
                        }
                    });
        }
        int bexCount = recipe.bex;
        if (bexCount != 0) {
            if (bexCount < 0) {
                bexCount = 1;
            }
            builder.addSlot(RecipeIngredientRole.INPUT, 67, 15).addItemStack(new ItemStack(MHItems.BRONSON_EXTRACT.get(), bexCount))
                    .addTooltipCallback((tooltipContext, tooltip) -> {
                        if (recipe.bex == -1) {
                            tooltip.add(Component.translatable("des.min3halla.recipe.optional").withStyle(ChatFormatting.GOLD));
                        }
                    });
        }
        int pwdCount = recipe.pwd;
        if (pwdCount != 0) {
            if (pwdCount < 0) {
                pwdCount = 1;
            }
            builder.addSlot(RecipeIngredientRole.INPUT, 127, 15).addItemStack(new ItemStack(MHItems.POWDERED_DELTA.get(), pwdCount))
                    .addTooltipCallback((tooltipContext, tooltip) -> {
                        if (recipe.pwd == -1) {
                            tooltip.add(Component.translatable("des.min3halla.recipe.optional").withStyle(ChatFormatting.GOLD));
                        }
                    });
        }
        int flaCount = recipe.fla;
        if (flaCount != 0) {
            if (flaCount < 0) {
                flaCount = 1;
            }
            builder.addSlot(RecipeIngredientRole.INPUT, 7, 53).addItemStack(new ItemStack(MHItems.FLANERGIDE.get(), flaCount))
                    .addTooltipCallback((tooltipContext, tooltip) -> {
                        if (recipe.fla == -1) {
                            tooltip.add(Component.translatable("des.min3halla.recipe.optional").withStyle(ChatFormatting.GOLD));
                        }
                    });
        }
        int karCount = recipe.kar;
        if (karCount != 0) {
            if (karCount < 0) {
                karCount = 1;
            }
            builder.addSlot(RecipeIngredientRole.INPUT, 127, 53).addItemStack(new ItemStack(MHItems.KARMOTRINE.get(), karCount))
                    .addTooltipCallback((tooltipContext, tooltip) -> {
                        if (recipe.kar == -1) {
                            tooltip.add(Component.translatable("des.min3halla.recipe.optional").withStyle(ChatFormatting.GOLD));
                        }
                    });
        }

        if (recipe.ice) {
            builder.addSlot(RecipeIngredientRole.INPUT, 5, 81).addItemStack(new ItemStack(Items.ICE));
        }
        if (recipe.age) {
            builder.addSlot(RecipeIngredientRole.INPUT, 41, 81).addItemStack(new ItemStack(Items.REDSTONE));
        }
        if (!recipe.dye.isEmpty()) {
            builder.addSlot(RecipeIngredientRole.INPUT, 123, 81).addIngredients(recipe.dye);
        }
        if (!recipe.extra.isEmpty()) {
            builder.addSlot(RecipeIngredientRole.INPUT, 161, 81).addIngredients(recipe.extra);
        }

        builder.addSlot(RecipeIngredientRole.INPUT, 82, 79).addItemStack(new ItemStack(MHItems.SHAKER_POT.get()))
                .addTooltipCallback((tooltipContext, tooltip) -> {
                    if (recipe.blend) {
                        tooltip.add(Component.translatable("des.min3halla.shaker_pot.blend").append(" (10s+)").withStyle(ChatFormatting.GOLD));
                    } else {
                        tooltip.add(Component.translatable("des.min3halla.shaker_pot.mix").append(" (5 ~ 10s)").withStyle(ChatFormatting.YELLOW));
                    }
                });

        ItemStack result = recipe.getResultItem(null);
        if (recipe.big) {
            result.getOrCreateTag().putBoolean("Big", true);
        }

        builder.addSlot(RecipeIngredientRole.OUTPUT, 82, 53).addItemStack(result);
    }
}
