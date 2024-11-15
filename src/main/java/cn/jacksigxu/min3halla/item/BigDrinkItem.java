package cn.jacksigxu.min3halla.item;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BigDrinkItem extends DrinkItem {

    public BigDrinkItem(FoodProperties foodProperties) {
        super(foodProperties);
    }

    public BigDrinkItem(FoodProperties foodProperties, int alcohol) {
        super(foodProperties);
        this.alcohol = alcohol;
    }

    @Override
    public int getUseDuration(ItemStack pStack) {
        return 64;
    }

    @Override
    public @Nullable FoodProperties getFoodProperties(ItemStack stack, @Nullable LivingEntity entity) {
        return super.getFoodProperties(stack, entity);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("des." + pStack.getDescriptionId().split("item.")[1])
                .withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));

        pTooltipComponents.add(Component.translatable("des.min3halla.big").withStyle(ChatFormatting.WHITE));

        makeTagTooltip(pStack, pTooltipComponents);

        if (this.alcohol > 0) {
            pTooltipComponents.add(Component.translatable("des.min3halla.alcohol", this.alcohol).withStyle(ChatFormatting.AQUA));
        } else if (this.alcohol == -1) {
            if (pStack.getTag() != null && pStack.getTag().contains("Alcohol") && pStack.getTag().getInt("Alcohol") > 0) {
                pTooltipComponents.add(Component.translatable("des.min3halla.alcohol", pStack.getTag().getInt("Alcohol")).withStyle(ChatFormatting.AQUA));
            }
        }
    }

}
