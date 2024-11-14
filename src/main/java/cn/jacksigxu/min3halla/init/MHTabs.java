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

                pOutput.accept(MHItems.SHAKER_POT.get());
                pOutput.accept(MHItems.WINE_GLASS.get());
                pOutput.accept(MHItems.ADELHYDE.get());
                pOutput.accept(MHItems.BRONSON_EXTRACT.get());
                pOutput.accept(MHItems.POWDERED_DELTA.get());
                pOutput.accept(MHItems.FLANERGIDE.get());
                pOutput.accept(MHItems.KARMOTRINE.get());
                pOutput.accept(MHItems.BTC_CAN.get());

                // Drinks
                pOutput.accept(makeWineStack(MHItems.BAD_TOUCH.get(), 4));
                pOutput.accept(makeWineStack(MHItems.BEER.get(), 4));
                pOutput.accept(makeWineStack(MHItems.BLEEDING_JANE.get(), 0));
                pOutput.accept(makeWineStack(MHItems.BLOOM_LIGHT.get(), 3));
                pOutput.accept(makeWineStack(MHItems.BLUE_FAIRY.get(), 0));
                pOutput.accept(makeWineStack(MHItems.BRANDTINI.get(), 1));
                pOutput.accept(makeWineStack(MHItems.COBALT_VELVET.get(), 5));
                pOutput.accept(makeWineStack(MHItems.CREVICE_SPIKE.get(), 0));
                pOutput.accept(makeWineStack(MHItems.FLAMING_MOAI.get(), 5));
                pOutput.accept(makeWineStack(MHItems.FLUFFY_DREAM.get(), 0));
                pOutput.accept(makeWineStack(MHItems.FRINGE_WEAVER.get(), 9));
                pOutput.accept(makeWineStack(MHItems.FROTHY_WATER.get(), 0));
                pOutput.accept(makeWineStack(MHItems.GRIZZLY_TEMPLE.get(), 1));
                pOutput.accept(makeWineStack(MHItems.GUT_PUNCH.get(), 0));
                pOutput.accept(makeWineStack(MHItems.MARSBLAST.get(), 2));
                pOutput.accept(makeWineStack(MHItems.MERCURYBLAST.get(), 2));
                pOutput.accept(makeWineStack(MHItems.MOONBLAST.get(), 2));
                pOutput.accept(makeWineStack(MHItems.PIANO_MAN.get(), 3));
                pOutput.accept(makeWineStack(MHItems.PIANO_WOMAN.get(), 3));
                pOutput.accept(makeWineStack(MHItems.PILE_DRIVER.get(), 4));
                pOutput.accept(makeWineStack(MHItems.SPARKLE_STAR.get(), 0));
                pOutput.accept(makeWineStack(MHItems.SUGAR_RUSH.get(), 0));
                pOutput.accept(makeWineStack(MHItems.SUNSHINE_CLOUD.get(), 0));
                pOutput.accept(makeWineStack(MHItems.SUPLEX.get(), 3));
                pOutput.accept(makeWineStack(MHItems.ZEN_STAR.get(), 4));

                // Bottle Drinks
                pOutput.accept(MHItems.RUM.get());

                pOutput.accept(makeWineStack(MHItems.ERROR_DRINK.get(), 20));

                // Big Drinks
                pOutput.accept(makeWineStack(MHItems.SUGAR_RUSH.get(), 0, true));
            })
            .build();

    private static ItemStack makeWineStack(ItemLike itemLike, int alcohol) {
        return makeWineStack(itemLike, alcohol, false);
    }

    private static ItemStack makeWineStack(ItemLike itemLike, int alcohol, boolean big) {
        ItemStack stack = new ItemStack(itemLike);
        if (alcohol > 0) {
            stack.getOrCreateTag().putInt("Alcohol", alcohol);
        }
        if (big) {
            stack.getOrCreateTag().putBoolean("Big", true);
        }
        return stack;
    }

    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MHMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> ITEM_TAB = TABS.register("mh_tab", () -> MH_TAB);

    public static void register(IEventBus bus) {
        TABS.register(bus);
    }

}
