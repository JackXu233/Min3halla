package cn.jacksigxu.min3halla.item;

import cn.jacksigxu.min3halla.init.MHItems;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
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

import java.util.List;

public class DrinkItem extends Item {

    private boolean hasRemainingItem = false;

    public DrinkItem(FoodProperties foodProperties) {
        super(new Properties().food(foodProperties).stacksTo(16));
    }

    public DrinkItem(FoodProperties foodProperties, boolean hasRemainingItem) {
        super(new Properties().food(foodProperties).stacksTo(16));
        this.hasRemainingItem = hasRemainingItem;
    }

    @Override
    public ItemStack getCraftingRemainingItem(ItemStack itemStack) {
        ItemStack res = itemStack.copy();
        res.setCount(1);
        return hasRemainingItem ? res : super.getCraftingRemainingItem(itemStack);
    }

    @Override
    public boolean hasCraftingRemainingItem(ItemStack stack) {
        return this.hasRemainingItem;
    }

    @Override
    public int getUseDuration(ItemStack pStack) {
        if (pStack.getTag() != null && pStack.getTag().contains("Big") && pStack.getTag().getBoolean("Big")) {
            return super.getUseDuration(pStack) * 2;
        } else {
            return super.getUseDuration(pStack);
        }
    }

    @Override
    public @Nullable FoodProperties getFoodProperties(ItemStack stack, @Nullable LivingEntity entity) {
        if (stack.getTag() != null && stack.getTag().contains("Big") && stack.getTag().getBoolean("Big")) {
            var food = super.getFoodProperties(stack, entity);
            var properties = new FoodProperties.Builder();
            if (food != null) {
                properties.nutrition(food.getNutrition() * 2)
                        .saturationMod(food.getSaturationModifier());
                if (food.canAlwaysEat()) {
                    properties.alwaysEat();
                }
                if (food.isMeat()) {
                    properties.meat();
                }
                for (var effect : food.getEffects()) {
                    properties.effect(effect::getFirst, effect.getSecond());
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
        if (pStack.getTag() != null) {
            if (pStack.getTag().contains("Big") && pStack.getTag().getBoolean("Big")) {
                pTooltipComponents.add(Component.translatable("des.min3halla.big").withStyle(ChatFormatting.WHITE));
            }
            if (pStack.getTag().contains("Alcohol") && pStack.getTag().getInt("Alcohol") > 0) {
                pTooltipComponents.add(Component.literal(""));
                pTooltipComponents.add(Component.translatable("des.min3halla.alcohol", pStack.getTag().getInt("Alcohol")).withStyle(ChatFormatting.AQUA));
            }
        }
    }

    @Override
    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity) {
        int alcohol = pStack.getOrCreateTag().getInt("Alcohol");
        if (alcohol > 5) {
            if (pLevel.random.nextDouble() < (alcohol - 5) * 0.06) {
                pLivingEntity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, alcohol * 20, 0));
            }
        }
        if (alcohol > 10) {
            if (pLevel.random.nextDouble() < (alcohol - 10) * 0.075) {
                pLivingEntity.addEffect(new MobEffectInstance(MobEffects.HUNGER, alcohol * 20, 0));
            }
        }
        if (alcohol > 15) {
            if (pLevel.random.nextDouble() < (alcohol - 15) * 0.2) {
                pLivingEntity.addEffect(new MobEffectInstance(MobEffects.POISON, alcohol * 20, 0));
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
