package cn.jacksigxu.min3halla.datagen;

import cn.jacksigxu.min3halla.MHMod;
import cn.jacksigxu.min3halla.init.MHItems;
import cn.jacksigxu.min3halla.init.MHTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {

    public ModItemTagProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> providerCompletableFuture,
                              CompletableFuture<TagLookup<Block>> tagLookupCompletableFuture, @Nullable ExistingFileHelper existingFileHelper) {
        super(packOutput, providerCompletableFuture, tagLookupCompletableFuture, MHMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(MHTags.Items.FLAVOR_SWEET).add(MHItems.BLUE_FAIRY.get(), MHItems.BRANDTINI.get(), MHItems.MOONBLAST.get(),
                MHItems.PIANO_WOMAN.get(), MHItems.SPARKLE_STAR.get(), MHItems.SUGAR_RUSH.get());
        this.tag(MHTags.Items.FLAVOR_SPICY).add(MHItems.BLEEDING_JANE.get(), MHItems.BLOOM_LIGHT.get(), MHItems.MARSBLAST.get());
        this.tag(MHTags.Items.FLAVOR_SOUR).add(MHItems.BAD_TOUCH.get(), MHItems.CREVICE_SPIKE.get(), MHItems.FLAMING_MOAI.get(),
                MHItems.FLUFFY_DREAM.get(), MHItems.MERCURYBLAST.get(), MHItems.PIANO_MAN.get(), MHItems.ZEN_STAR.get());
        this.tag(MHTags.Items.FLAVOR_BITTER).add(MHItems.GRIZZLY_TEMPLE.get(), MHItems.GUT_PUNCH.get(), MHItems.PILE_DRIVER.get(),
                MHItems.SUNSHINE_CLOUD.get(), MHItems.SUPLEX.get());
        this.tag(MHTags.Items.FLAVOR_BUBBLY).add(MHItems.BEER.get(), MHItems.COBALT_VELVET.get(), MHItems.FRINGE_WEAVER.get(),
                MHItems.FROTHY_WATER.get());

        this.tag(MHTags.Items.TYPE_CLASSIC).add(MHItems.BEER.get(), MHItems.FROTHY_WATER.get(), MHItems.BLEEDING_JANE.get());
        this.tag(MHTags.Items.TYPE_CLASSY).add(MHItems.COBALT_VELVET.get(), MHItems.FRINGE_WEAVER.get(), MHItems.BAD_TOUCH.get(),
                MHItems.FLAMING_MOAI.get(), MHItems.MERCURYBLAST.get(), MHItems.BRANDTINI.get());
        this.tag(MHTags.Items.TYPE_GIRLY).add(MHItems.SUNSHINE_CLOUD.get(), MHItems.FLUFFY_DREAM.get(), MHItems.BLUE_FAIRY.get(),
                MHItems.MOONBLAST.get(), MHItems.SPARKLE_STAR.get(), MHItems.SUGAR_RUSH.get());
        this.tag(MHTags.Items.TYPE_MANLY).add(MHItems.GUT_PUNCH.get(), MHItems.PILE_DRIVER.get(), MHItems.SUPLEX.get(),
                MHItems.CREVICE_SPIKE.get(), MHItems.MARSBLAST.get());
        this.tag(MHTags.Items.TYPE_PROMO).add(MHItems.GRIZZLY_TEMPLE.get(), MHItems.PIANO_MAN.get(), MHItems.ZEN_STAR.get(),
                MHItems.BLOOM_LIGHT.get(), MHItems.PIANO_WOMAN.get());

        this.tag(MHTags.Items.SECONDARY_BLAND).add(MHItems.FROTHY_WATER.get(), MHItems.GRIZZLY_TEMPLE.get(), MHItems.ZEN_STAR.get(),
                MHItems.BLOOM_LIGHT.get());
        this.tag(MHTags.Items.SECONDARY_BURNING).add(MHItems.COBALT_VELVET.get(), MHItems.MERCURYBLAST.get(), MHItems.PILE_DRIVER.get(),
                MHItems.SUPLEX.get());
        this.tag(MHTags.Items.SECONDARY_HAPPY).add(MHItems.BRANDTINI.get(), MHItems.MOONBLAST.get(), MHItems.SPARKLE_STAR.get(),
                MHItems.SUGAR_RUSH.get(), MHItems.PIANO_WOMAN.get());
        this.tag(MHTags.Items.SECONDARY_SOBERING).add(MHItems.BLEEDING_JANE.get(), MHItems.CREVICE_SPIKE.get());
        this.tag(MHTags.Items.SECONDARY_SOFT).add(MHItems.SUNSHINE_CLOUD.get(), MHItems.FLUFFY_DREAM.get(), MHItems.BLUE_FAIRY.get());
        this.tag(MHTags.Items.SECONDARY_STRONG).add(MHItems.FRINGE_WEAVER.get(), MHItems.GUT_PUNCH.get(), MHItems.MARSBLAST.get(),
                MHItems.PIANO_MAN.get());
        this.tag(MHTags.Items.SECONDARY_VINTAGE).add(MHItems.BAD_TOUCH.get(), MHItems.BEER.get());

        this.tag(MHTags.Items.BOTTLED).add(MHItems.MULAN_TEA.get(), MHItems.A_FEDORA.get(), MHItems.RUM.get(), MHItems.ABSINTHE.get());
    }
}
