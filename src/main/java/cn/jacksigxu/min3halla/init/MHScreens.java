package cn.jacksigxu.min3halla.init;

import cn.jacksigxu.min3halla.gui.screen.DrinkMixerScreen;
import cn.jacksigxu.min3halla.gui.screen.FermentBarrelScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class MHScreens {

    @SubscribeEvent
    public static void onClientSetUpEvent(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            MenuScreens.register(MHMenuTypes.DRINK_MIXER_MENU.get(), DrinkMixerScreen::new);
            MenuScreens.register(MHMenuTypes.FERMENT_BARREL_MENU.get(), FermentBarrelScreen::new);
        });
    }
}
