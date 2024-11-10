package cn.jacksigxu.min3halla.init;

import cn.jacksigxu.min3halla.MHMod;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class MHItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MHMod.MOD_ID);

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }

}
