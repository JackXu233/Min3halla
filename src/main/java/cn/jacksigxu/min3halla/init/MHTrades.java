package cn.jacksigxu.min3halla.init;

import cn.jacksigxu.min3halla.MHMod;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = MHMod.MOD_ID)
public class MHTrades {

    @SubscribeEvent
    public static void addWandererTrade(WandererTradesEvent event) {
        List<VillagerTrades.ItemListing> genericTrades = event.getGenericTrades();

        genericTrades.add(((pTrader, pRandom) -> new MerchantOffer(new ItemStack(Items.EMERALD, 32),
                new ItemStack(MHItems.MULAN_TEA.get(), 1), 4, 0, 0.15f)));
        genericTrades.add(((pTrader, pRandom) -> new MerchantOffer(new ItemStack(Items.EMERALD, 32),
                new ItemStack(MHItems.A_FEDORA.get(), 1), 4, 0, 0.15f)));
        genericTrades.add(((pTrader, pRandom) -> new MerchantOffer(new ItemStack(Items.EMERALD, 32),
                new ItemStack(MHItems.RUM.get(), 1), 4, 0, 0.15f)));
        genericTrades.add(((pTrader, pRandom) -> new MerchantOffer(new ItemStack(Items.EMERALD, 32),
                new ItemStack(MHItems.ABSINTHE.get(), 1), 4, 0, 0.15f)));
    }
}
