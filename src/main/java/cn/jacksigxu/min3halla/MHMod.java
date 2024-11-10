package cn.jacksigxu.min3halla;

import cn.jacksigxu.min3halla.init.MHItems;
import cn.jacksigxu.min3halla.init.MHTabs;
import com.mojang.logging.LogUtils;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(MHMod.MOD_ID)
public class MHMod {

    public static final String MOD_ID = "min3halla";

    private static final Logger LOGGER = LogUtils.getLogger();

    public MHMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        MHItems.register(modEventBus);
        MHTabs.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }

}
