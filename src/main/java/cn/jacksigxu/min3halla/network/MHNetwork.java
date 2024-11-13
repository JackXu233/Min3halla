package cn.jacksigxu.min3halla.network;

import cn.jacksigxu.min3halla.MHMod;
import cn.jacksigxu.min3halla.network.message.MixMessage;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class MHNetwork {

    public static final String NETWORK_VERSION = "1.0";
    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(MHMod.loc("network"),
            () -> NETWORK_VERSION, NETWORK_VERSION::equals, NETWORK_VERSION::equals);

    public static void init() {
        CHANNEL.registerMessage(0, MixMessage.class, MixMessage::encode, MixMessage::decode, MixMessage::handler);
    }
}
