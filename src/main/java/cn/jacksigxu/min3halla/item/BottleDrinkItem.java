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

public class BottleDrinkItem extends DrinkItem {

    public BottleDrinkItem(FoodProperties foodProperties) {
        super(foodProperties);
    }

    public BottleDrinkItem(FoodProperties foodProperties, int alcohol) {
        super(foodProperties);
        this.alcohol = alcohol;
    }

    @Override
    public ItemStack getCraftingRemainingItem(ItemStack itemStack) {
        ItemStack res = itemStack.copy();
        res.setCount(1);
        return res;
    }

    @Override
    public boolean hasCraftingRemainingItem(ItemStack stack) {
        return true;
    }

    @Override
    public int getUseDuration(ItemStack pStack) {
        return 32;
    }

    @Override
    public @Nullable FoodProperties getFoodProperties(ItemStack stack, @Nullable LivingEntity entity) {
        return super.getFoodProperties(stack, entity);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("des." + pStack.getDescriptionId().split("item.")[1])
                .withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));

        makeTagTooltip(pStack, pTooltipComponents);
    }

}
