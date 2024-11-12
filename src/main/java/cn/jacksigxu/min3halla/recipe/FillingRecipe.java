package cn.jacksigxu.min3halla.recipe;

import cn.jacksigxu.min3halla.MHMod;
import com.google.gson.JsonObject;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class FillingRecipe implements Recipe<SimpleContainer> {

    private final Ingredient input;
    private final Ingredient input2;
    private final ItemStack output;
    private final ResourceLocation id;

    public FillingRecipe(Ingredient input, Ingredient input2, ItemStack output, ResourceLocation id) {
        this.input = input;
        this.input2 = input2;
        this.output = output;
        this.id = id;
    }

    @Override
    public boolean matches(SimpleContainer pContainer, Level pLevel) {
        if(pLevel.isClientSide()) return false;
        return input.test(pContainer.getItem(0)) && input2.test(pContainer.getItem(1));
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> ingredients = NonNullList.withSize(2, Ingredient.EMPTY);
        ingredients.set(0, input);
        ingredients.set(1, input2);
        return ingredients;
    }

    @Override
    public ItemStack assemble(SimpleContainer pContainer, RegistryAccess pRegistryAccess) {
        return output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess pRegistryAccess) {
        return output.copy();
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<FillingRecipe>{
        public static final Type INSTANCE = new Type();
        public static final String ID = "filling";
    }

    public static class Serializer implements RecipeSerializer<FillingRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = MHMod.loc("filling");

        @Override
        public FillingRecipe fromJson(ResourceLocation recipeLoc, JsonObject recipeJson) {
            Ingredient input = Ingredient.fromJson(GsonHelper.getAsJsonObject(recipeJson, "input"));
            Ingredient input2 = Ingredient.fromJson(GsonHelper.getAsJsonObject(recipeJson, "input2"));
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(recipeJson, "output"));
            return new FillingRecipe(input, input2, output, recipeLoc);
        }

        @Override
        public @Nullable FillingRecipe fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf pBuffer) {
            Ingredient input = Ingredient.fromNetwork(pBuffer);
            Ingredient input2 = Ingredient.fromNetwork(pBuffer);
            ItemStack output = pBuffer.readItem();
            if(output.isEmpty()) return null;
            return new FillingRecipe(input, input2, output, pRecipeId);
        }

        @Override
        public void toNetwork(FriendlyByteBuf pBuffer, FillingRecipe pRecipe) {
            pRecipe.input.toNetwork(pBuffer);
            pRecipe.input2.toNetwork(pBuffer);
            pBuffer.writeItemStack(pRecipe.getResultItem(null), false);
        }
    }
}
