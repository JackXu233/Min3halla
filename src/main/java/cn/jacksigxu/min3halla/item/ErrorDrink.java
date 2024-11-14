package cn.jacksigxu.min3halla.item;

import cn.jacksigxu.min3halla.init.MHFoodProperties;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ErrorDrink extends DrinkItem {

    public ErrorDrink() {
        super(MHFoodProperties.ERROR_DRINK, 20);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("des.min3halla.error_drink").withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.OBFUSCATED));
    }
}
