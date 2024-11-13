package cn.jacksigxu.min3halla.item;

import cn.jacksigxu.min3halla.init.MHItems;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ShakerPot extends Item {

    public ShakerPot() {
        super(new Properties().stacksTo(1));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack stack = pPlayer.getItemInHand(pUsedHand);
        if (stack.getTag() == null || !stack.getTag().contains("Result")) {
            return InteractionResultHolder.fail(stack);
        }
        if (stack.getTag().contains("Finished") && stack.getTag().getBoolean("Finished")) {
            ItemStack glass = pPlayer.getItemInHand(pUsedHand == InteractionHand.MAIN_HAND ? InteractionHand.OFF_HAND : InteractionHand.MAIN_HAND);
            if (glass.is(MHItems.WINE_GLASS.get())) {
                if (!pPlayer.getAbilities().instabuild) {
                    glass.shrink(1);
                }
                var result = stack.getTag().getCompound("Result");
                pPlayer.addItem(ItemStack.of(result));

                stack.removeTagKey("Result");
                stack.removeTagKey("Finished");
                stack.removeTagKey("Blend");
                return InteractionResultHolder.success(stack);
            }

            return InteractionResultHolder.fail(stack);
        }

        pPlayer.startUsingItem(pUsedHand);
        return InteractionResultHolder.consume(stack);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if (pStack.getTag() != null && pStack.getTag().contains("Result")) {
            CompoundTag tag = pStack.getTag().getCompound("Result");
            ItemStack res = ItemStack.of(tag);
            pTooltipComponents.add(res.getHoverName());

            if (pStack.getTag().contains("Finished") && pStack.getTag().getBoolean("Finished")) {
                pTooltipComponents.add(Component.literal("Finished!").withStyle(ChatFormatting.GREEN));
            }
        }
    }

    @Override
    public int getUseDuration(ItemStack pStack) {
        return 72000;
    }

    @Override
    public void onStopUsing(ItemStack stack, LivingEntity entity, int count) {
        int duration = getUseDuration(stack) - count;
        if (duration < 100) return;

        if (stack.getTag() == null || !stack.getTag().contains("Blend") || !stack.getTag().contains("Result")) return;
        boolean blend = stack.getTag().getBoolean("Blend");
        if (blend) {
            if (duration >= 200) {
                stack.getTag().putBoolean("Finished", true);
            }
        } else {
            if (duration >= 200) {
                ItemStack result = new ItemStack(MHItems.ERROR_DRINK.get());
                stack.getTag().put("Result", result.serializeNBT());
            }
            stack.getTag().putBoolean("Finished", true);
        }
    }

    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.BRUSH;
    }
}
