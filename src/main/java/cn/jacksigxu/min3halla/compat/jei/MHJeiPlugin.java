package cn.jacksigxu.min3halla.compat.jei;

import cn.jacksigxu.min3halla.MHMod;
import cn.jacksigxu.min3halla.gui.screen.DrinkMixerScreen;
import cn.jacksigxu.min3halla.init.MHItems;
import cn.jacksigxu.min3halla.recipe.MixingRecipe;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@JeiPlugin
public class MHJeiPlugin implements IModPlugin {

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new MixingCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(MHItems.DRINK_MIXER.get()), MixingCategory.TYPE);
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();

        // 调饮台
        List<MixingRecipe> mixingRecipes = recipeManager.getAllRecipesFor(MixingRecipe.Type.INSTANCE);
        registration.addRecipes(MixingCategory.TYPE, mixingRecipes.stream().filter(recipe -> !recipe.big).toList());
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(DrinkMixerScreen.class, 98, 88, 46, 13, MixingCategory.TYPE);
    }

    @Override
    public @NotNull ResourceLocation getPluginUid() {
        return MHMod.loc("jei_plugin");
    }
}
