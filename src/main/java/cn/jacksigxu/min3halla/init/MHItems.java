package cn.jacksigxu.min3halla.init;

import cn.jacksigxu.min3halla.MHMod;
import cn.jacksigxu.min3halla.item.ShakerPot;
import cn.jacksigxu.min3halla.item.ingredient.*;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@SuppressWarnings("unused")
public class MHItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MHMod.MOD_ID);

    public static final RegistryObject<Item> SHAKER_POT = ITEMS.register("shake_pot", ShakerPot::new);

    public static final RegistryObject<Item> ADELHYDE = ITEMS.register("adelhyde", Adelhyde::new);
    public static final RegistryObject<Item> BRONSON_EXTRACT = ITEMS.register("bronson_extract", BronsonExtract::new);
    public static final RegistryObject<Item> POWDERED_DELTA = ITEMS.register("powdered_delta", PowderedDelta::new);
    public static final RegistryObject<Item> FLANERGIDE = ITEMS.register("flanergide", Flanergide::new);
    public static final RegistryObject<Item> KARMOTRINE = ITEMS.register("karmotrine", Karmotrine::new);

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }

}
