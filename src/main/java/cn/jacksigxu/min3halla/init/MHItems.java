package cn.jacksigxu.min3halla.init;

import cn.jacksigxu.min3halla.MHMod;
import cn.jacksigxu.min3halla.item.ShakerPot;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MHItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MHMod.MOD_ID);

    public static final RegistryObject<Item> SHAKER_POT = ITEMS.register("shake_pot", ShakerPot::new);

    public static final RegistryObject<Item> ADELHYDE = ITEMS.register("adelhyde", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> BRONSON_EXTRACT = ITEMS.register("bronson_extract", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> POWDERED_DELTA = ITEMS.register("powdered_delta", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> FLANERGIDE = ITEMS.register("flanergide", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> KARMOTRINE = ITEMS.register("karmotrine", () -> new Item(new Item.Properties()));

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }

}
