package cn.jacksigxu.min3halla.network.message;

import cn.jacksigxu.min3halla.gui.menu.DrinkMixerMenu;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class MixMessage {

    private final int id;

    public MixMessage(int id) {
        this.id = id;
    }

    public static void encode(MixMessage message, FriendlyByteBuf buffer) {
        buffer.writeInt(message.id);
    }

    public static MixMessage decode(FriendlyByteBuf buffer) {
        return new MixMessage(buffer.readInt());
    }

    public static void handler(MixMessage message, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            if (player == null) return;

            AbstractContainerMenu abstractcontainermenu = player.containerMenu;
            if (abstractcontainermenu instanceof DrinkMixerMenu menu) {
                if (!player.containerMenu.stillValid(player)) {
                    return;
                }
                menu.doCraftItem();
            }
        });
        ctx.get().setPacketHandled(true);
    }

}
