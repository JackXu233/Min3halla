package cn.jacksigxu.min3halla.network.message;

import cn.jacksigxu.min3halla.gui.menu.DrinkMixerMenu;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class IngredientAdjustMessage {

    private final int index;
    private final boolean increase;

    public IngredientAdjustMessage(int index, boolean increase) {
        this.index = index;
        this.increase = increase;
    }

    public static void encode(IngredientAdjustMessage message, FriendlyByteBuf buffer) {
        buffer.writeInt(message.index);
        buffer.writeBoolean(message.increase);
    }

    public static IngredientAdjustMessage decode(FriendlyByteBuf buffer) {
        return new IngredientAdjustMessage(buffer.readInt(), buffer.readBoolean());
    }

    public static void handler(IngredientAdjustMessage message, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            if (player == null) return;

            AbstractContainerMenu abstractcontainermenu = player.containerMenu;
            if (abstractcontainermenu instanceof DrinkMixerMenu menu) {
                if (!player.containerMenu.stillValid(player)) {
                    return;
                }
                menu.adjustIngredient(message.index, message.increase);
            }
        });
        ctx.get().setPacketHandled(true);
    }

}
