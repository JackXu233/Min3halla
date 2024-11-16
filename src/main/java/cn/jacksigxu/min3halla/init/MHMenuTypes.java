package cn.jacksigxu.min3halla.init;

import cn.jacksigxu.min3halla.MHMod;
import cn.jacksigxu.min3halla.gui.menu.BTCFillingMachineMenu;
import cn.jacksigxu.min3halla.gui.menu.DrinkMixerMenu;
import cn.jacksigxu.min3halla.gui.menu.FermentBarrelMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MHMenuTypes {

    public static final DeferredRegister<MenuType<?>> MENU_TYPES = DeferredRegister.create(ForgeRegistries.MENU_TYPES, MHMod.MOD_ID);

    public static final RegistryObject<MenuType<DrinkMixerMenu>> DRINK_MIXER_MENU =
            MENU_TYPES.register("drink_mixer_menu",
                    () -> IForgeMenuType.create((windowId, inv, data) -> new DrinkMixerMenu(windowId, inv)));
    public static final RegistryObject<MenuType<FermentBarrelMenu>> FERMENT_BARREL_MENU =
            MENU_TYPES.register("ferment_barrel_menu",
                    () -> IForgeMenuType.create((windowId, inv, data) -> new FermentBarrelMenu(windowId, inv)));
    public static final RegistryObject<MenuType<BTCFillingMachineMenu>> BTC_FILLING_MACHINE_MENU =
            MENU_TYPES.register("btc_filling_machine_menu",
                    () -> IForgeMenuType.create((windowId, inv, data) -> new BTCFillingMachineMenu(windowId, inv)));
}
