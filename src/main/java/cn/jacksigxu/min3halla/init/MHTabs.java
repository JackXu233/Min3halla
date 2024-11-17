package cn.jacksigxu.min3halla.init;

import cn.jacksigxu.min3halla.MHMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class MHTabs {

    public static CreativeModeTab MH_TAB = CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.min3halla_tab"))
            .icon(() -> new ItemStack(MHItems.SUGAR_RUSH.get()))
            .displayItems((pParameters, pOutput) -> {
                pOutput.accept(MHItems.DRINK_MIXER.get());
                pOutput.accept(MHItems.FERMENT_BARREL.get());
                pOutput.accept(MHItems.BTC_FILLING_MACHINE.get());

                pOutput.accept(MHItems.SHAKER_POT.get());
                pOutput.accept(MHItems.WINE_GLASS.get());
                pOutput.accept(MHItems.ADELHYDE.get());
                pOutput.accept(MHItems.BRONSON_EXTRACT.get());
                pOutput.accept(MHItems.POWDERED_DELTA.get());
                pOutput.accept(MHItems.FLANERGIDE.get());
                pOutput.accept(MHItems.KARMOTRINE.get());
                pOutput.accept(MHItems.BTC_CAN.get());
                pOutput.accept(MHItems.HYPER_ACID_X.get());
                pOutput.accept(MHItems.GLITCH_LIQUID.get());

                // Drinks
                pOutput.accept(MHItems.BAD_TOUCH.get());
                pOutput.accept(MHItems.BEER.get());
                pOutput.accept(MHItems.BLEEDING_JANE.get());
                pOutput.accept(MHItems.BLOOM_LIGHT.get());
                pOutput.accept(MHItems.BLUE_FAIRY.get());
                pOutput.accept(MHItems.BRANDTINI.get());
                pOutput.accept(MHItems.COBALT_VELVET.get());
                pOutput.accept(MHItems.CREVICE_SPIKE.get());
                pOutput.accept(MHItems.FLUFFY_DREAM.get());
                pOutput.accept(MHItems.FRINGE_WEAVER.get());
                pOutput.accept(MHItems.FROTHY_WATER.get());
                pOutput.accept(MHItems.GRIZZLY_TEMPLE.get());
                pOutput.accept(MHItems.GUT_PUNCH.get());
                pOutput.accept(MHItems.MARSBLAST.get());
                pOutput.accept(MHItems.MERCURYBLAST.get());
                pOutput.accept(MHItems.MOONBLAST.get());
                pOutput.accept(MHItems.PIANO_MAN.get());
                pOutput.accept(MHItems.PIANO_WOMAN.get());
                pOutput.accept(MHItems.PILE_DRIVER.get());
                pOutput.accept(MHItems.SPARKLE_STAR.get());
                pOutput.accept(MHItems.SUGAR_RUSH.get());
                pOutput.accept(MHItems.SUNSHINE_CLOUD.get());
                pOutput.accept(MHItems.SUPLEX.get());
                pOutput.accept(MHItems.ZEN_STAR.get());

                // Bottle Drinks
                pOutput.accept(MHItems.MULAN_TEA.get());
                pOutput.accept(MHItems.A_FEDORA.get());
                pOutput.accept(MHItems.RUM.get());
                pOutput.accept(MHItems.ABSINTHE.get());

                pOutput.accept(MHItems.ERROR_DRINK.get());

                // Non-Natural Big Drinks
//                pOutput.accept(makeBigWineStack(MHItems.BAD_TOUCH.get()));
//                pOutput.accept(makeBigWineStack(MHItems.BEER.get()));
//                pOutput.accept(makeBigWineStack(MHItems.BLEEDING_JANE.get()));
//                pOutput.accept(makeBigWineStack(MHItems.BLOOM_LIGHT.get()));
//                pOutput.accept(makeBigWineStack(MHItems.BLUE_FAIRY.get()));
//                pOutput.accept(makeBigWineStack(MHItems.BRANDTINI.get()));
//                pOutput.accept(makeBigWineStack(MHItems.COBALT_VELVET.get()));
//                pOutput.accept(makeBigWineStack(MHItems.CREVICE_SPIKE.get()));
//                pOutput.accept(makeBigWineStack(MHItems.FLUFFY_DREAM.get()));
//                pOutput.accept(makeBigWineStack(MHItems.FRINGE_WEAVER.get()));
//                pOutput.accept(makeBigWineStack(MHItems.FROTHY_WATER.get()));
//                pOutput.accept(makeBigWineStack(MHItems.GRIZZLY_TEMPLE.get()));
//                pOutput.accept(makeBigWineStack(MHItems.GUT_PUNCH.get()));
//                pOutput.accept(makeBigWineStack(MHItems.MERCURYBLAST.get()));
//                pOutput.accept(makeBigWineStack(MHItems.MOONBLAST.get()));
//                pOutput.accept(makeBigWineStack(MHItems.PILE_DRIVER.get()));
//                pOutput.accept(makeBigWineStack(MHItems.SPARKLE_STAR.get()));
//                pOutput.accept(makeBigWineStack(MHItems.SUGAR_RUSH.get()));
//                pOutput.accept(makeBigWineStack(MHItems.SUNSHINE_CLOUD.get()));
//                pOutput.accept(makeBigWineStack(MHItems.SUPLEX.get()));

                // Original Drinks
                pOutput.accept(MHItems.SUPERB_BASKETBALL.get());
                pOutput.accept(MHItems.SEVEN_WOLVES.get());
                pOutput.accept(MHItems.ALARM_LAMP.get());
                pOutput.accept(MHItems.AFTERGLOW.get());
            })
            .build();

    private static ItemStack makeBigWineStack(ItemLike itemLike) {
        return makeBigWineStack(itemLike, 0);
    }

    private static ItemStack makeBigWineStack(ItemLike itemLike, int alcohol) {
        ItemStack stack = new ItemStack(itemLike);
        if (alcohol > 0) {
            stack.getOrCreateTag().putInt("Alcohol", alcohol);
        }
        stack.getOrCreateTag().putBoolean("Big", true);
        return stack;
    }

    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MHMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> ITEM_TAB = TABS.register("mh_tab", () -> MH_TAB);

    public static void register(IEventBus bus) {
        TABS.register(bus);
    }

}
