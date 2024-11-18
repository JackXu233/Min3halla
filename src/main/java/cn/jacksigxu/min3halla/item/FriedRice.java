package cn.jacksigxu.min3halla.item;

import cn.jacksigxu.min3halla.init.MHFoodProperties;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.game.ClientboundExplodePacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class FriedRice extends Item {

    public FriedRice() {
        super(new Properties().food(MHFoodProperties.FRIED_RICE).stacksTo(16));
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("des.min3halla.fried_rice").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level worldIn, LivingEntity entityLiving) {
        if (entityLiving instanceof Player player && !worldIn.isClientSide) {
            Explosion explosion = new Explosion(worldIn, player, worldIn.damageSources().explosion(player, player),
                    null, player.getX(), player.getY(), player.getZ(), 3, false, Explosion.BlockInteraction.KEEP);
            explosion.explode();
            explosion.finalizeExplosion(true);

            explosion.clearToBlow();

            for (ServerPlayer serverPlayer : ((ServerLevel) worldIn).players()) {
                if (serverPlayer.distanceToSqr(player.getX(), player.getY(), player.getZ()) < 256) {
                    serverPlayer.connection.send(new ClientboundExplodePacket(player.getX(), player.getY(), player.getZ(), 2, explosion.getToBlow(), explosion.getHitPlayers().get(player)));
                }
            }
        }
        return super.finishUsingItem(stack, worldIn, entityLiving);
    }

}
