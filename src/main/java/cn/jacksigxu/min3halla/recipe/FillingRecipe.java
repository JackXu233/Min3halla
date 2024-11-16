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
import net.minecraftforge.common.util.RecipeMatcher;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class FillingRecipe implements Recipe<SimpleContainer> {

    private final NonNullList<Ingredient> inputs;
    private final ItemStack output;
    private final ResourceLocation id;

    public FillingRecipe(NonNullList<Ingredient> inputs, ItemStack output, ResourceLocation id) {
        this.inputs = inputs;
        this.output = output;
        this.id = id;
    }

    @Override
    public boolean matches(SimpleContainer pContainer, Level pLevel) {
        if (pLevel.isClientSide()) return false;

        List<ItemStack> inputs = new ArrayList<>();
        int i = 0;

        for (int j = 0; j < pContainer.getContainerSize(); ++j) {
            ItemStack itemstack = pContainer.getItem(j);
            if (!itemstack.isEmpty()) {
                ++i;
                inputs.add(itemstack);
            }
        }

        return i == this.inputs.size() && RecipeMatcher.findMatches(inputs, this.inputs) != null;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return inputs;
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

    public static class Type implements RecipeType<FillingRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "filling";
    }

    public static class Serializer implements RecipeSerializer<FillingRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = MHMod.loc("filling");

        @Override
        public FillingRecipe fromJson(ResourceLocation recipeLoc, JsonObject recipeJson) {
            NonNullList<Ingredient> inputs = NonNullList.create();
            var ingredients = GsonHelper.getAsJsonArray(recipeJson, "ingredients");
            for (int i = 0; i < ingredients.size(); i++) {
                inputs.add(Ingredient.fromJson(ingredients.get(i).getAsJsonObject()));
            }
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(recipeJson, "output"));
            return new FillingRecipe(inputs, output, recipeLoc);
        }

        @Override
        public @Nullable FillingRecipe fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf pBuffer) {
            int size = pBuffer.readVarInt();
            NonNullList<Ingredient> inputs = NonNullList.withSize(size, Ingredient.EMPTY);
            for (int i = 0; i < size; i++) {
                inputs.set(i, Ingredient.fromNetwork(pBuffer));
            }
            ItemStack output = pBuffer.readItem();
            return new FillingRecipe(inputs, output, pRecipeId);
        }

        @Override
        public void toNetwork(FriendlyByteBuf pBuffer, FillingRecipe pRecipe) {
            pBuffer.writeVarInt(pRecipe.inputs.size());
            for (Ingredient ingredient : pRecipe.inputs) {
                ingredient.toNetwork(pBuffer);
            }
            pBuffer.writeItemStack(pRecipe.getResultItem(null), false);
        }
    }
}
