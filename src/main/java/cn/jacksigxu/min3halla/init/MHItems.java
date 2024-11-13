package cn.jacksigxu.min3halla.init;

import cn.jacksigxu.min3halla.MHMod;
import cn.jacksigxu.min3halla.item.DrinkItem;
import cn.jacksigxu.min3halla.item.ErrorDrink;
import cn.jacksigxu.min3halla.item.ShakerPot;
import cn.jacksigxu.min3halla.item.ingredient.*;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@SuppressWarnings("unused")
public class MHItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MHMod.MOD_ID);

    public static final RegistryObject<Item> DRINK_MIXER = ITEMS.register("drink_mixer",
            () -> new BlockItem(MHBlocks.DRINK_MIXER.get(), new Item.Properties()));
    public static final RegistryObject<Item> FERMENT_BARREL = ITEMS.register("ferment_barrel",
            () -> new BlockItem(MHBlocks.FERMENT_BARREL.get(), new Item.Properties()));

    public static final RegistryObject<Item> SHAKER_POT = ITEMS.register("shaker_pot", ShakerPot::new);
    public static final RegistryObject<Item> WINE_GLASS = ITEMS.register("wine_glass", () -> new Item(new Item.Properties().stacksTo(16)));

    public static final RegistryObject<Item> ADELHYDE = ITEMS.register("adelhyde", Adelhyde::new);
    public static final RegistryObject<Item> BRONSON_EXTRACT = ITEMS.register("bronson_extract", BronsonExtract::new);
    public static final RegistryObject<Item> POWDERED_DELTA = ITEMS.register("powdered_delta", PowderedDelta::new);
    public static final RegistryObject<Item> FLANERGIDE = ITEMS.register("flanergide", Flanergide::new);
    public static final RegistryObject<Item> KARMOTRINE = ITEMS.register("karmotrine", Karmotrine::new);
    public static final RegistryObject<Item> BTC_CAN = ITEMS.register("btc_can", () -> new Item(new Item.Properties()));

    // Drinks
    public static final RegistryObject<Item> BAD_TOUCH = ITEMS.register("bad_touch", () -> new DrinkItem(MHFoodProperties.BAD_TOUCH));
    public static final RegistryObject<Item> BEER = ITEMS.register("beer", () -> new DrinkItem(MHFoodProperties.BEER));
    public static final RegistryObject<Item> BLEEDING_JANE = ITEMS.register("bleeding_jane", () -> new DrinkItem(MHFoodProperties.BLEEDING_JANE));
    public static final RegistryObject<Item> BLOOM_LIGHT = ITEMS.register("bloom_light", () -> new DrinkItem(MHFoodProperties.BLOOM_LIGHT));
    public static final RegistryObject<Item> BLUE_FAIRY = ITEMS.register("blue_fairy", () -> new DrinkItem(MHFoodProperties.BLUE_FAIRY));
    public static final RegistryObject<Item> BRANDTINI = ITEMS.register("brandtini", () -> new DrinkItem(MHFoodProperties.BRANDTINI));

    public static final RegistryObject<Item> SUGAR_RUSH = ITEMS.register("sugar_rush", () -> new DrinkItem(MHFoodProperties.SUGAR_RUSH));
    public static final RegistryObject<Item> ZEN_STAR = ITEMS.register("zen_star", () -> new DrinkItem(MHFoodProperties.ZEN_STAR));

    public static final RegistryObject<Item> RUM = ITEMS.register("rum", () -> new DrinkItem(MHFoodProperties.RUM));

    public static final RegistryObject<Item> ERROR_DRINK = ITEMS.register("error_drink", ErrorDrink::new);

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }

}
