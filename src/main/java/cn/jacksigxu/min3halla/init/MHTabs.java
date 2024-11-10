package cn.jacksigxu.min3halla.init;

import cn.jacksigxu.min3halla.MHMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;

public class MHTabs {

    private static final List<RegistryObject<Item>> HIDDEN = new ArrayList<>();

    public static CreativeModeTab MH_TAB = CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.min3halla_tab"))
            .icon(() -> new ItemStack(Items.BAMBOO))
            .displayItems((pParameters, pOutput) -> MHItems.ITEMS.getEntries().forEach((itemRegistryObject) -> {
                if (!HIDDEN.contains(itemRegistryObject)) {
                    pOutput.accept(itemRegistryObject.get());
                }
            }))
            .build();

    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MHMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> ITEM_TAB = TABS.register("mh_tab", () -> MH_TAB);

    public static void register(IEventBus bus) {
        TABS.register(bus);
    }

}
