package cn.jacksigxu.min3halla.recipe;

import cn.jacksigxu.min3halla.MHMod;
import com.google.gson.JsonObject;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class FermentingRecipe implements Recipe<SimpleContainer> {

    private final Ingredient input;
    private final ItemStack output;
    private final ResourceLocation id;

    public FermentingRecipe(Ingredient input, ItemStack output, ResourceLocation id) {
        this.input = input;
        this.output = output;
        this.id = id;
    }

    @Override
    public boolean matches(SimpleContainer pContainer, Level pLevel) {
        if (pLevel.isClientSide()) return false;
        return input.test(pContainer.getItem(0));
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
    public ItemStack getResultItem(@Nullable RegistryAccess pRegistryAccess) {
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

    public Ingredient getInput() {
        return input;
    }

    public static class Type implements RecipeType<FermentingRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "fermenting";
    }

    public static class Serializer implements RecipeSerializer<FermentingRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = MHMod.loc("fermenting");

        @Override
        public FermentingRecipe fromJson(ResourceLocation recipeLoc, JsonObject recipeJson) {
            Ingredient input = Ingredient.fromJson(GsonHelper.getAsJsonObject(recipeJson, "input"));
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(recipeJson, "output"));
            return new FermentingRecipe(input, output, recipeLoc);
        }

        @Override
        public FermentingRecipe fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf pBuffer) {
            Ingredient input = Ingredient.fromNetwork(pBuffer);
            ItemStack output = pBuffer.readItem();
            return new FermentingRecipe(input, output, pRecipeId);
        }

        @Override
        public void toNetwork(FriendlyByteBuf pBuffer, FermentingRecipe pRecipe) {
            pRecipe.input.toNetwork(pBuffer);
            pBuffer.writeItemStack(pRecipe.getResultItem(null), false);
        }
    }
}
