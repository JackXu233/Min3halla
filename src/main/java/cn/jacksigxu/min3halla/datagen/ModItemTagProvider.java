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
        this.tag(MHTags.Items.FLAVOR_SWEET).add(MHItems.BLUE_FAIRY.get(), MHItems.SUGAR_RUSH.get());
        this.tag(MHTags.Items.FLAVOR_SPICY).add(MHItems.BLEEDING_JANE.get(), MHItems.BLOOM_LIGHT.get());
        this.tag(MHTags.Items.FLAVOR_SOUR).add(MHItems.BAD_TOUCH.get(), MHItems.ZEN_STAR.get());
//        this.tag(MHTags.Items.FLAVOR_BITTER).add();
        this.tag(MHTags.Items.FLAVOR_BUBBLY).add(MHItems.BEER.get());

        this.tag(MHTags.Items.TYPE_CLASSIC).add(MHItems.BEER.get(), MHItems.BLEEDING_JANE.get());
        this.tag(MHTags.Items.TYPE_CLASSY).add(MHItems.BAD_TOUCH.get(), MHItems.BRANDTINI.get());
        this.tag(MHTags.Items.TYPE_GIRLY).add(MHItems.BLUE_FAIRY.get(), MHItems.SUGAR_RUSH.get());
//        this.tag(MHTags.Items.TYPE_MANLY).add();
        this.tag(MHTags.Items.TYPE_PROMO).add(MHItems.ZEN_STAR.get(), MHItems.BLOOM_LIGHT.get());
    }
}
