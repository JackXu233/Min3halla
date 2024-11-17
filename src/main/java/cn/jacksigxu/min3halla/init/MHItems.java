package cn.jacksigxu.min3halla.init;

import cn.jacksigxu.min3halla.MHMod;
import cn.jacksigxu.min3halla.item.*;
import cn.jacksigxu.min3halla.item.ingredient.*;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MHItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MHMod.MOD_ID);

    public static final RegistryObject<Item> DRINK_MIXER = ITEMS.register("drink_mixer",
            () -> new BlockItem(MHBlocks.DRINK_MIXER.get(), new Item.Properties()));
    public static final RegistryObject<Item> FERMENT_BARREL = ITEMS.register("ferment_barrel",
            () -> new BlockItem(MHBlocks.FERMENT_BARREL.get(), new Item.Properties()));
    public static final RegistryObject<Item> BTC_FILLING_MACHINE = ITEMS.register("btc_filling_machine",
            () -> new BlockItem(MHBlocks.BTC_FILLING_MACHINE.get(), new Item.Properties()));

    public static final RegistryObject<Item> SHAKER_POT = ITEMS.register("shaker_pot", ShakerPot::new);
    public static final RegistryObject<Item> WINE_GLASS = ITEMS.register("wine_glass", () -> new Item(new Item.Properties().stacksTo(16)));

    public static final RegistryObject<Item> ADELHYDE = ITEMS.register("adelhyde", Adelhyde::new);
    public static final RegistryObject<Item> BRONSON_EXTRACT = ITEMS.register("bronson_extract", BronsonExtract::new);
    public static final RegistryObject<Item> POWDERED_DELTA = ITEMS.register("powdered_delta", PowderedDelta::new);
    public static final RegistryObject<Item> FLANERGIDE = ITEMS.register("flanergide", Flanergide::new);
    public static final RegistryObject<Item> KARMOTRINE = ITEMS.register("karmotrine", Karmotrine::new);
    public static final RegistryObject<Item> BTC_CAN = ITEMS.register("btc_can", () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> HYPER_ACID_X = ITEMS.register("hyper_acid_x", () -> new Item(new Item.Properties().food(MHFoodProperties.HYPER_ACID_X)));
    public static final RegistryObject<Item> GLITCH_LIQUID = ITEMS.register("glitch_liquid", () -> new Item(new Item.Properties().food(MHFoodProperties.GLITCH_LIQUID)));

    // Drinks
    public static final RegistryObject<Item> BAD_TOUCH = ITEMS.register("bad_touch", () -> new DrinkItem(MHFoodProperties.BAD_TOUCH, 4));
    public static final RegistryObject<Item> BEER = ITEMS.register("beer", () -> new DrinkItem(MHFoodProperties.BEER, 4));
    public static final RegistryObject<Item> BLEEDING_JANE = ITEMS.register("bleeding_jane", () -> new DrinkItem(MHFoodProperties.BLEEDING_JANE));
    public static final RegistryObject<Item> BLOOM_LIGHT = ITEMS.register("bloom_light", () -> new DrinkItem(MHFoodProperties.BLOOM_LIGHT, 3));
    public static final RegistryObject<Item> BLUE_FAIRY = ITEMS.register("blue_fairy", () -> new DrinkItem(MHFoodProperties.BLUE_FAIRY, -1));
    public static final RegistryObject<Item> BRANDTINI = ITEMS.register("brandtini", () -> new DrinkItem(MHFoodProperties.BRANDTINI, 1));
    public static final RegistryObject<Item> COBALT_VELVET = ITEMS.register("cobalt_velvet", () -> new DrinkItem(MHFoodProperties.COBALT_VELVET, 5));
    public static final RegistryObject<Item> CREVICE_SPIKE = ITEMS.register("crevice_spike", () -> new DrinkItem(MHFoodProperties.CREVICE_SPIKE, -1));
    public static final RegistryObject<Item> FLAMING_MOAI = ITEMS.register("flaming_moai", () -> new BigDrinkItem(MHFoodProperties.FLAMING_MOAI, 5));
    public static final RegistryObject<Item> FLUFFY_DREAM = ITEMS.register("fluffy_dream", () -> new DrinkItem(MHFoodProperties.FLUFFY_DREAM, -1));
    public static final RegistryObject<Item> FRINGE_WEAVER = ITEMS.register("fringe_weaver", () -> new DrinkItem(MHFoodProperties.FRINGE_WEAVER, 9));
    public static final RegistryObject<Item> FROTHY_WATER = ITEMS.register("frothy_water", () -> new DrinkItem(MHFoodProperties.FROTHY_WATER));
    public static final RegistryObject<Item> GRIZZLY_TEMPLE = ITEMS.register("grizzly_temple", () -> new DrinkItem(MHFoodProperties.GRIZZLY_TEMPLE, 1));
    public static final RegistryObject<Item> GUT_PUNCH = ITEMS.register("gut_punch", () -> new DrinkItem(MHFoodProperties.GUT_PUNCH, -1));
    public static final RegistryObject<Item> MARSBLAST = ITEMS.register("marsblast", () -> new BigDrinkItem(MHFoodProperties.MARSBLAST, 2));
    public static final RegistryObject<Item> MERCURYBLAST = ITEMS.register("mercuryblast", () -> new DrinkItem(MHFoodProperties.MERCURYBLAST, 2));
    public static final RegistryObject<Item> MOONBLAST = ITEMS.register("moonblast", () -> new DrinkItem(MHFoodProperties.MOONBLAST, 2));
    public static final RegistryObject<Item> PIANO_MAN = ITEMS.register("piano_man", () -> new BigDrinkItem(MHFoodProperties.PIANO_MAN, 3));
    public static final RegistryObject<Item> PIANO_WOMAN = ITEMS.register("piano_woman", () -> new BigDrinkItem(MHFoodProperties.PIANO_WOMAN, 3));
    public static final RegistryObject<Item> PILE_DRIVER = ITEMS.register("pile_driver", () -> new DrinkItem(MHFoodProperties.PILE_DRIVER, 4));
    public static final RegistryObject<Item> SPARKLE_STAR = ITEMS.register("sparkle_star", () -> new DrinkItem(MHFoodProperties.SPARKLE_STAR, -1));
    public static final RegistryObject<Item> SUGAR_RUSH = ITEMS.register("sugar_rush", () -> new DrinkItem(MHFoodProperties.SUGAR_RUSH, -1));
    public static final RegistryObject<Item> SUNSHINE_CLOUD = ITEMS.register("sunshine_cloud", () -> new DrinkItem(MHFoodProperties.SUNSHINE_CLOUD, -1));
    public static final RegistryObject<Item> SUPLEX = ITEMS.register("suplex", () -> new DrinkItem(MHFoodProperties.SUPLEX, 3));
    public static final RegistryObject<Item> ZEN_STAR = ITEMS.register("zen_star", () -> new BigDrinkItem(MHFoodProperties.ZEN_STAR, 4));
    public static final RegistryObject<Item> MULAN_TEA = ITEMS.register("mulan_tea", () -> new BottleDrinkItem(MHFoodProperties.MULAN_TEA, 0));
    public static final RegistryObject<Item> A_FEDORA = ITEMS.register("a_fedora", () -> new BottleDrinkItem(MHFoodProperties.A_FEDORA, 0));
    public static final RegistryObject<Item> RUM = ITEMS.register("rum", () -> new BottleDrinkItem(MHFoodProperties.RUM, 0));
    public static final RegistryObject<Item> ABSINTHE = ITEMS.register("absinthe", () -> new BottleDrinkItem(MHFoodProperties.ABSINTHE, 0));

    public static final RegistryObject<Item> ERROR_DRINK = ITEMS.register("error_drink", ErrorDrink::new);

    // Original Drinks
    public static final RegistryObject<Item> SUPERB_BASKETBALL = ITEMS.register("superb_basketball", () -> new BigDrinkItem(MHFoodProperties.SUPERB_BASKETBALL));
    public static final RegistryObject<Item> SEVEN_WOLVES = ITEMS.register("seven_wolves", () -> new DrinkItem(MHFoodProperties.SEVEN_WOLVES, 3));
    public static final RegistryObject<Item> ALARM_LAMP = ITEMS.register("alarm_lamp", () -> new BigDrinkItem(MHFoodProperties.ALARM_LAMP, 6));
    public static final RegistryObject<Item> AFTERGLOW = ITEMS.register("afterglow", () -> new BigDrinkItem(MHFoodProperties.AFTERGLOW, 4));

    public static void register(IEventBus bus) {
        ITEMS.register(bus);
    }

}
