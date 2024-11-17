package cn.jacksigxu.min3halla.item;

import cn.jacksigxu.min3halla.init.MHItems;
import cn.jacksigxu.min3halla.init.MHTags;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DrinkItem extends Item {

    // 默认为0，设置为-1表示这是任意酒精度的饮品
    protected int alcohol = 0;

    public DrinkItem(FoodProperties foodProperties) {
        super(new Properties().food(foodProperties).stacksTo(16));
    }

    public DrinkItem(FoodProperties foodProperties, int alcohol) {
        super(new Properties().food(foodProperties).stacksTo(16));
        this.alcohol = alcohol;
    }

    @Override
    public int getUseDuration(ItemStack pStack) {
        if (pStack.getTag() != null && pStack.getTag().contains("Big") && pStack.getTag().getBoolean("Big")) {
            return 64;
        } else {
            return 32;
        }
    }

    @Override
    public @Nullable FoodProperties getFoodProperties(ItemStack stack, @Nullable LivingEntity entity) {
        if (stack.getTag() != null && stack.getTag().contains("Big") && stack.getTag().getBoolean("Big")) {
            var food = super.getFoodProperties(stack, entity);
            var properties = new FoodProperties.Builder();
            if (food != null) {
                properties.nutrition(food.getNutrition() * 2).saturationMod(food.getSaturationModifier());
                if (food.canAlwaysEat()) {
                    properties.alwaysEat();
                }
                if (food.isMeat()) {
                    properties.meat();
                }
                for (var effect : food.getEffects()) {
                    properties.effect(effect::getFirst, Math.min(1, effect.getSecond() * 2));
                }
                return properties.build();
            }
        }

        return super.getFoodProperties(stack, entity);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("des." + pStack.getDescriptionId().split("item.")[1])
                .withStyle(ChatFormatting.GRAY).withStyle(ChatFormatting.ITALIC));

        if (pStack.getTag() != null && pStack.getTag().contains("Big") && pStack.getTag().getBoolean("Big")) {
            pTooltipComponents.add(Component.translatable("des.min3halla.big").withStyle(ChatFormatting.WHITE));
        }

        makeTagTooltip(pStack, pTooltipComponents);

        if (this.alcohol == -1) {
            if (pStack.getTag() != null && pStack.getTag().contains("Alcohol") && pStack.getTag().getInt("Alcohol") > 0) {
                pTooltipComponents.add(Component.translatable("des.min3halla.alcohol", pStack.getTag().getInt("Alcohol")).withStyle(ChatFormatting.AQUA));
            }
        }
    }

    protected void makeTagTooltip(ItemStack stack, List<Component> tooltips) {
        if (!Screen.hasShiftDown()) {
            return;
        }

        List<MutableComponent> temp = new ArrayList<>();
        // Flavor
        MutableComponent flavor = null;
        if (stack.is(MHTags.Items.FLAVOR_SWEET)) {
            flavor = Component.translatable("des.min3halla.flavor.sweet");
        } else if (stack.is(MHTags.Items.FLAVOR_SPICY)) {
            flavor = Component.translatable("des.min3halla.flavor.spicy");
        } else if (stack.is(MHTags.Items.FLAVOR_SOUR)) {
            flavor = Component.translatable("des.min3halla.flavor.sour");
        } else if (stack.is(MHTags.Items.FLAVOR_BITTER)) {
            flavor = Component.translatable("des.min3halla.flavor.bitter");
        } else if (stack.is(MHTags.Items.FLAVOR_BUBBLY)) {
            flavor = Component.translatable("des.min3halla.flavor.bubbly");
        }
        if (flavor != null) {
            temp.add(flavor);
        }

        // Type
        MutableComponent type = null;
        if (stack.is(MHTags.Items.TYPE_CLASSIC)) {
            type = Component.translatable("des.min3halla.type.classic");
        } else if (stack.is(MHTags.Items.TYPE_CLASSY)) {
            type = Component.translatable("des.min3halla.type.classy");
        } else if (stack.is(MHTags.Items.TYPE_GIRLY)) {
            type = Component.translatable("des.min3halla.type.girly");
        } else if (stack.is(MHTags.Items.TYPE_MANLY)) {
            type = Component.translatable("des.min3halla.type.manly");
        } else if (stack.is(MHTags.Items.TYPE_PROMO)) {
            type = Component.translatable("des.min3halla.type.promo");
        }
        if (type != null) {
            temp.add(type);
        }

        // Secondary Type
        MutableComponent secondaryType = null;
        if (stack.is(MHTags.Items.SECONDARY_BLAND)) {
            secondaryType = Component.translatable("des.min3halla.secondary.bland");
        } else if (stack.is(MHTags.Items.SECONDARY_BURNING)) {
            secondaryType = Component.translatable("des.min3halla.secondary.burning");
        } else if (stack.is(MHTags.Items.SECONDARY_HAPPY)) {
            secondaryType = Component.translatable("des.min3halla.secondary.happy");
        } else if (stack.is(MHTags.Items.SECONDARY_SOBERING)) {
            secondaryType = Component.translatable("des.min3halla.secondary.sobering");
        } else if (stack.is(MHTags.Items.SECONDARY_SOFT)) {
            secondaryType = Component.translatable("des.min3halla.secondary.soft");
        } else if (stack.is(MHTags.Items.SECONDARY_STRONG)) {
            secondaryType = Component.translatable("des.min3halla.secondary.strong");
        } else if (stack.is(MHTags.Items.SECONDARY_VINTAGE)) {
            secondaryType = Component.translatable("des.min3halla.secondary.vintage");
        }
        if (secondaryType != null) {
            temp.add(secondaryType);
        }

        if (temp.isEmpty()) return;
        MutableComponent result = temp.get(0);
        for (int i = 1; i < temp.size(); i++) {
            result.append(", ").append(temp.get(i));
        }

        tooltips.add(result.withStyle(ChatFormatting.YELLOW));
    }

    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity) {
        // 酒精含量不固定的饮品，才根据加入的酒精量进行效果的处理，固定饮品则在FoodProperties中进行处理
        if (this.alcohol == -1) {
            int alcohol = pStack.getOrCreateTag().getInt("Alcohol");

            if (!pLevel.isClientSide) {
                if (pLevel.random.nextDouble() < alcohol * 0.1) {
                    pLivingEntity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 300, 0));
                }
            }
        }

        ItemStack itemstack = super.finishUsingItem(pStack, pLevel, pLivingEntity);
        ItemStack result = new ItemStack(MHItems.WINE_GLASS.get());

        if (itemstack.isEmpty()) {
            return result;
        } else {
            if (pLivingEntity instanceof Player player) {
                if (!player.getAbilities().instabuild && !player.getInventory().add(result)) {
                    player.drop(result, false);
                }
            }
            return itemstack;
        }
    }

    @Override
    public SoundEvent getEatingSound() {
        return SoundEvents.GENERIC_DRINK;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.DRINK;
    }
}
