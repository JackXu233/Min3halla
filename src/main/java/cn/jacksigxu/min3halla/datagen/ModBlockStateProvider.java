package cn.jacksigxu.min3halla.datagen;

import cn.jacksigxu.min3halla.MHMod;
import cn.jacksigxu.min3halla.init.MHBlocks;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, MHMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        horizontalBlock(MHBlocks.DRINK_MIXER.get(), new ModelFile.UncheckedModelFile(modLoc("block/drink_mixer")));
        simpleBlockWithItem(MHBlocks.FERMENT_BARREL.get(), models().cubeBottomTop("ferment_barrel", MHMod.loc("block/ferment_barrel_side"),
                MHMod.loc("block/ferment_barrel_bottom"), MHMod.loc("block/ferment_barrel_top")));
        simpleBlockWithItem(MHBlocks.BTC_FILLING_MACHINE.get(), models().cube("btc_filling_machine", MHMod.loc("block/btc_filling_machine_top"),
                MHMod.loc("block/btc_filling_machine_top"), MHMod.loc("block/btc_filling_machine_north"), MHMod.loc("block/btc_filling_machine_side"),
                MHMod.loc("block/btc_filling_machine_side"), MHMod.loc("block/btc_filling_machine_side")));
    }
}
