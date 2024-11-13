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

                pOutput.accept(makeWineStack(MHItems.BAD_TOUCH.get(), 4));
                pOutput.accept(makeWineStack(MHItems.BEER.get(), 4));
                pOutput.accept(makeWineStack(MHItems.BLEEDING_JANE.get(), 0));
                pOutput.accept(makeWineStack(MHItems.BLOOM_LIGHT.get(), 3));
                pOutput.accept(makeWineStack(MHItems.BLUE_FAIRY.get(), 0));
                pOutput.accept(makeWineStack(MHItems.BRANDTINI.get(), 1));

                pOutput.accept(makeWineStack(MHItems.SUGAR_RUSH.get(), 0));
                pOutput.accept(makeWineStack(MHItems.ZEN_STAR.get(), 4));

                pOutput.accept(makeWineStack(MHItems.ERROR_DRINK.get(), 20));
            })
            .build();

    private static ItemStack makeWineStack(ItemLike itemLike, int alcohol) {
        ItemStack stack = new ItemStack(itemLike);
        if (alcohol > 0) {
            stack.getOrCreateTag().putInt("Alcohol", alcohol);
        }
        return stack;
    }

    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MHMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> ITEM_TAB = TABS.register("mh_tab", () -> MH_TAB);

    public static void register(IEventBus bus) {
        TABS.register(bus);
    }

}
