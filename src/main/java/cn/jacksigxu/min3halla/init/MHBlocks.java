package cn.jacksigxu.min3halla.init;

import cn.jacksigxu.min3halla.MHMod;
import cn.jacksigxu.min3halla.block.DrinkMixerBlock;
import cn.jacksigxu.min3halla.block.FermentBarrelBlock;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MHBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MHMod.MOD_ID);

    public static final RegistryObject<Block> DRINK_MIXER = BLOCKS.register("drink_mixer", DrinkMixerBlock::new);
    public static final RegistryObject<Block> FERMENT_BARREL = BLOCKS.register("ferment_barrel", FermentBarrelBlock::new);
}
