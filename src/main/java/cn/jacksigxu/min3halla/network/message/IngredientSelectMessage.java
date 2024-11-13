package cn.jacksigxu.min3halla.network.message;

import cn.jacksigxu.min3halla.gui.menu.DrinkMixerMenu;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class IngredientSelectMessage {

    private final int index;

    public IngredientSelectMessage(int index) {
        this.index = index;
    }

    public static void encode(IngredientSelectMessage message, FriendlyByteBuf buffer) {
        buffer.writeInt(message.index);
    }

    public static IngredientSelectMessage decode(FriendlyByteBuf buffer) {
        return new IngredientSelectMessage(buffer.readInt());
    }

    public static void handler(IngredientSelectMessage message, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            if (player == null) return;

            AbstractContainerMenu abstractcontainermenu = player.containerMenu;
            if (abstractcontainermenu instanceof DrinkMixerMenu menu) {
                if (!player.containerMenu.stillValid(player)) {
                    return;
                }
                menu.setIngredientSelected(message.index);
            }
        });
        ctx.get().setPacketHandled(true);
    }

}
