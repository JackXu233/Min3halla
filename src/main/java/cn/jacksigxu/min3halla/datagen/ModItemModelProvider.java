package cn.jacksigxu.min3halla.datagen;

import cn.jacksigxu.min3halla.MHMod;
import cn.jacksigxu.min3halla.init.MHBlocks;
import cn.jacksigxu.min3halla.init.MHItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@SuppressWarnings({"ConstantConditions", "UnusedReturnValue", "SameParameterValue", "unused"})
public class ModItemModelProvider extends ItemModelProvider {

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MHMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        evenSimplerBlockItem(MHBlocks.DRINK_MIXER);

        simpleItem(MHItems.ADELHYDE);
        simpleItem(MHItems.BRONSON_EXTRACT);
        simpleItem(MHItems.POWDERED_DELTA);
        simpleItem(MHItems.FLANERGIDE);
        simpleItem(MHItems.KARMOTRINE);
        simpleItem(MHItems.BTC_CAN);
        simpleItem(MHItems.WINE_GLASS);

        simpleItem(MHItems.BAD_TOUCH);
//        simpleItem(MHItems.BEER);
        simpleItem(MHItems.BLEEDING_JANE);
        simpleItem(MHItems.BLOOM_LIGHT);
        simpleItem(MHItems.BLUE_FAIRY);
        simpleItem(MHItems.BRANDTINI);
//        simpleItem(MHItems.COBALT_VELVET);
        simpleItem(MHItems.CREVICE_SPIKE);
//        simpleItem(MHItems.FLAMING_MOAI);
//        simpleItem(MHItems.FLUFFY_DREAM);
//        simpleItem(MHItems.FRINGE_WEAVER);
//        simpleItem(MHItems.FROTHY_WATER);
        simpleItem(MHItems.GRIZZLY_TEMPLE);

        simpleItem(MHItems.SUGAR_RUSH);
        simpleItem(MHItems.ZEN_STAR);

        simpleItem(MHItems.MULAN_TEA);
        simpleItem(MHItems.A_FEDORA);
        simpleItem(MHItems.RUM);
        simpleItem(MHItems.ABSINTHE);

        simpleItem(MHItems.ERROR_DRINK);
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(), new ResourceLocation("item/generated"))
                .texture("layer0", MHMod.loc("item/" + item.getId().getPath()));
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item, String renderType) {
        return simpleItem(item).renderType(renderType);
    }

    private ItemModelBuilder simpleItem(ResourceLocation item) {
        return withExistingParent(item.getPath(), new ResourceLocation("item/generated"))
                .texture("layer0", MHMod.loc("item/" + item.getPath()));
    }

    public void evenSimplerBlockItem(RegistryObject<Block> block) {
        this.withExistingParent(MHMod.MOD_ID + ":" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath(),
                modLoc("block/" + ForgeRegistries.BLOCKS.getKey(block.get()).getPath()));
    }

    private ItemModelBuilder handheldItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(), new ResourceLocation("item/handheld"))
                .texture("layer0", MHMod.loc("item/" + item.getId().getPath()));
    }

}
