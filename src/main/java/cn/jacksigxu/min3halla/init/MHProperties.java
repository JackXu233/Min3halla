package cn.jacksigxu.min3halla.init;

import cn.jacksigxu.min3halla.MHMod;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class MHProperties {

    @SubscribeEvent
    public static void propertyOverrideRegistry(FMLClientSetupEvent event) {
        event.enqueueWork(() -> ItemProperties.register(MHItems.SHAKER_POT.get(), MHMod.loc("type"),
                (stack, world, entity, seed) -> {
                    if (stack.getTag() == null) {
                        return 0.0F;
                    }
                    if (stack.getTag().contains("Finished") && stack.getTag().getBoolean("Finished")) {
                        return 2.0F;
                    }
                    if (stack.getTag().contains("Result")) {
                        return 1.0F;
                    }

                    return 0.0F;
                }));
    }
}
