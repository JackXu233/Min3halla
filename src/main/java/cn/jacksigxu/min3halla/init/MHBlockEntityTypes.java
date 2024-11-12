package cn.jacksigxu.min3halla.init;

import cn.jacksigxu.min3halla.MHMod;
import cn.jacksigxu.min3halla.block.entity.DrinkMixerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MHBlockEntityTypes {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MHMod.MOD_ID);

    public static final RegistryObject<BlockEntityType<DrinkMixerBlockEntity>> DRINK_MIXER_BLOCK_ENTITY =
            BLOCK_ENTITY_TYPES.register("drink_mixer_block_entity",
                    () -> BlockEntityType.Builder.of(DrinkMixerBlockEntity::new, MHBlocks.DRINK_MIXER.get()).build(null));

}
