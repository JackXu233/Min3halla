package cn.jacksigxu.min3halla.datagen;

import cn.jacksigxu.min3halla.init.MHBlocks;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootProvider extends BlockLootSubProvider {

    public ModBlockLootProvider() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        this.dropSelf(MHBlocks.DRINK_MIXER.get());
        this.dropSelf(MHBlocks.FERMENT_BARREL.get());
        this.dropSelf(MHBlocks.BTC_FILLING_MACHINE.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return MHBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
