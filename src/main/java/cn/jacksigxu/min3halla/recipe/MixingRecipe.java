package cn.jacksigxu.min3halla.recipe;

import cn.jacksigxu.min3halla.MHMod;
import cn.jacksigxu.min3halla.init.MHItems;
import com.google.gson.JsonObject;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class MixingRecipe implements Recipe<SimpleContainer> {

    public final int ade;
    public final int bex;
    public final int pwd;
    public final int fla;
    public final int kar;
    public final boolean ice;
    public final boolean age;
    public final Ingredient dye;
    public final Ingredient extra;
    public final boolean blend;
    private final ItemStack output;
    public final boolean big;
    private final ResourceLocation id;

    public MixingRecipe(int ade, int bex, int pwd, int fla, int kar, boolean ice, boolean age, Ingredient dye, Ingredient extra, boolean blend, ItemStack output, boolean big, ResourceLocation id) {
        this.ade = ade;
        this.bex = bex;
        this.pwd = pwd;
        this.fla = fla;
        this.kar = kar;
        this.ice = ice;
        this.age = age;
        this.dye = dye;
        this.extra = extra;
        this.blend = blend;
        this.output = output;
        this.big = big;
        this.id = id;
    }

    @Override
    public boolean matches(SimpleContainer pContainer, Level pLevel) {
        if (pLevel.isClientSide()) {
            return false;
        }

        return matchMixIngredient(ade, 0, pContainer) &&
                matchMixIngredient(bex, 1, pContainer) &&
                matchMixIngredient(pwd, 2, pContainer) &&
                matchMixIngredient(fla, 3, pContainer) &&
                matchMixIngredient(kar, 4, pContainer) &&
                ice == !pContainer.getItem(5).isEmpty() &&
                age == !pContainer.getItem(6).isEmpty() &&
                dye.test(pContainer.getItem(7)) &&
                extra.test(pContainer.getItem(8));
    }

    private boolean matchMixIngredient(int count, int index, SimpleContainer pContainer) {
        if (count == -1) return true;
        return count == pContainer.getItem(index).getCount();
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> ingredients = NonNullList.withSize(9, Ingredient.EMPTY);
        setMixIngredient(ingredients, 0, MHItems.ADELHYDE.get(), ade);
        setMixIngredient(ingredients, 1, MHItems.BRONSON_EXTRACT.get(), bex);
        setMixIngredient(ingredients, 2, MHItems.POWDERED_DELTA.get(), pwd);
        setMixIngredient(ingredients, 3, MHItems.FLANERGIDE.get(), fla);
        setMixIngredient(ingredients, 4, MHItems.KARMOTRINE.get(), kar);
        if (ice) {
            setMixIngredient(ingredients, 5, Items.ICE, 1);
        }
        if (age) {
            setMixIngredient(ingredients, 6, Items.REDSTONE, 1);
        }
        ingredients.set(7, dye);
        ingredients.set(8, extra);

        return ingredients;
    }

    private void setMixIngredient(NonNullList<Ingredient> ingredients, int index, Item item, int count) {
        if (count == -1) {
            count = 1;
        }
        ingredients.set(index, Ingredient.of(new ItemStack(item, count)));
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

    public static class Type implements RecipeType<MixingRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "mixing";
    }

    public static class Serializer implements RecipeSerializer<MixingRecipe> {
        public static final Serializer INSTANCE = new Serializer();

        public static final ResourceLocation ID = MHMod.loc("mixing");

        @Override
        public MixingRecipe fromJson(ResourceLocation pRecipeId, JsonObject pSerializedRecipe) {
            int ade = GsonHelper.getAsInt(pSerializedRecipe, "ade", 0);
            int bex = GsonHelper.getAsInt(pSerializedRecipe, "bex", 0);
            int pwd = GsonHelper.getAsInt(pSerializedRecipe, "pwd", 0);
            int fla = GsonHelper.getAsInt(pSerializedRecipe, "fla", 0);
            int kar = GsonHelper.getAsInt(pSerializedRecipe, "kar", 0);
            boolean ice = GsonHelper.getAsBoolean(pSerializedRecipe, "ice", false);
            boolean age = GsonHelper.getAsBoolean(pSerializedRecipe, "age", false);
            var dyeObject = GsonHelper.getAsJsonObject(pSerializedRecipe, "dye", null);
            Ingredient dye = dyeObject == null ? Ingredient.EMPTY : Ingredient.fromJson(dyeObject);
            var extraObject = GsonHelper.getAsJsonObject(pSerializedRecipe, "extra", null);
            Ingredient extra = extraObject == null ? Ingredient.EMPTY : Ingredient.fromJson(extraObject);
            boolean blend = GsonHelper.getAsBoolean(pSerializedRecipe, "blend", false);
            boolean big = GsonHelper.getAsBoolean(pSerializedRecipe, "big", false);
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "output"));

            return new MixingRecipe(ade, bex, pwd, fla, kar, ice, age, dye, extra, blend, output, big, pRecipeId);
        }

        @Override
        public @Nullable MixingRecipe fromNetwork(ResourceLocation pRecipeId, FriendlyByteBuf pBuffer) {
            int ade = pBuffer.readInt();
            int bex = pBuffer.readInt();
            int pwd = pBuffer.readInt();
            int fla = pBuffer.readInt();
            int kar = pBuffer.readInt();
            boolean ice = pBuffer.readBoolean();
            boolean age = pBuffer.readBoolean();
            Ingredient dye = Ingredient.fromNetwork(pBuffer);
            Ingredient extra = Ingredient.fromNetwork(pBuffer);
            boolean blend = pBuffer.readBoolean();
            boolean big = pBuffer.readBoolean();
            ItemStack output = pBuffer.readItem();
            if (output.isEmpty()) {
                return null;
            }

            return new MixingRecipe(ade, bex, pwd, fla, kar, ice, age, dye, extra, blend, output, big, pRecipeId);
        }

        @Override
        public void toNetwork(FriendlyByteBuf pBuffer, MixingRecipe pRecipe) {
            pBuffer.writeInt(pRecipe.ade);
            pBuffer.writeInt(pRecipe.bex);
            pBuffer.writeInt(pRecipe.pwd);
            pBuffer.writeInt(pRecipe.fla);
            pBuffer.writeInt(pRecipe.kar);
            pBuffer.writeBoolean(pRecipe.ice);
            pBuffer.writeBoolean(pRecipe.age);
            pRecipe.dye.toNetwork(pBuffer);
            pRecipe.extra.toNetwork(pBuffer);
            pBuffer.writeBoolean(pRecipe.blend);
            pBuffer.writeBoolean(pRecipe.big);
            pBuffer.writeItemStack(pRecipe.getResultItem(null), false);
        }
    }
}
