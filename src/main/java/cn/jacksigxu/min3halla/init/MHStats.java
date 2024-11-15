package cn.jacksigxu.min3halla.init;

import cn.jacksigxu.min3halla.MHMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class MHStats {

    public static final DeferredRegister<ResourceLocation> STATS = DeferredRegister.create(Registries.CUSTOM_STAT, MHMod.MOD_ID);

    public static final RegistryObject<ResourceLocation> INTERACT_WITH_DRINK_MIXER = STATS.register("interact_with_drink_mixer",
            () -> MHMod.loc("interact_with_drink_mixer"));

}
