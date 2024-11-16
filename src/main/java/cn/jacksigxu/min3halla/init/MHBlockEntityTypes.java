package cn.jacksigxu.min3halla.init;

import cn.jacksigxu.min3halla.MHMod;
import cn.jacksigxu.min3halla.block.entity.BTCFillingMachineBlockEntity;
import cn.jacksigxu.min3halla.block.entity.DrinkMixerBlockEntity;
import cn.jacksigxu.min3halla.block.entity.FermentBarrelBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MHBlockEntityTypes {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, MHMod.MOD_ID);

    public static final RegistryObject<BlockEntityType<DrinkMixerBlockEntity>> DRINK_MIXER_BLOCK_ENTITY =
            BLOCK_ENTITY_TYPES.register("drink_mixer_block_entity",
                    () -> BlockEntityType.Builder.of(DrinkMixerBlockEntity::new, MHBlocks.DRINK_MIXER.get()).build(null));
    public static final RegistryObject<BlockEntityType<FermentBarrelBlockEntity>> FERMENT_BARREL_BLOCK_ENTITY =
            BLOCK_ENTITY_TYPES.register("ferment_barrel_block_entity",
                    () -> BlockEntityType.Builder.of(FermentBarrelBlockEntity::new, MHBlocks.FERMENT_BARREL.get()).build(null));
    public static final RegistryObject<BlockEntityType<BTCFillingMachineBlockEntity>> BTC_FILLING_MACHINE_BLOCK_ENTITY =
            BLOCK_ENTITY_TYPES.register("btc_filling_machine_block_entity",
                    () -> BlockEntityType.Builder.of(BTCFillingMachineBlockEntity::new, MHBlocks.BTC_FILLING_MACHINE.get()).build(null));

}
