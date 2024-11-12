package cn.jacksigxu.min3halla.init;

import cn.jacksigxu.min3halla.MHMod;
import cn.jacksigxu.min3halla.recipe.FillingRecipe;
import cn.jacksigxu.min3halla.recipe.MixingRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MHRecipes {

    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, MHMod.MOD_ID);

    public static final RegistryObject<RecipeSerializer<MixingRecipe>> MIXING_SERIALIZER =
            RECIPE_SERIALIZERS.register("mixing", () -> MixingRecipe.Serializer.INSTANCE);
    public static final RegistryObject<RecipeSerializer<FillingRecipe>> FILLING_SERIALIZER =
            RECIPE_SERIALIZERS.register("filling", () -> FillingRecipe.Serializer.INSTANCE);

}
