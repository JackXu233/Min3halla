package cn.jacksigxu.min3halla.init;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class MHFoodProperties {

    public static final FoodProperties SUGAR_RUSH = new FoodProperties.Builder().nutrition(6).saturationMod(0.4F).alwaysEat().build();

    public static final FoodProperties ZEN_STAR = new FoodProperties.Builder().nutrition(5).saturationMod(0.4F).alwaysEat().build();

    public static final FoodProperties ERROR_DRINK = new FoodProperties.Builder().nutrition(1).saturationMod(0.2F).alwaysEat()
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 400, 0), 1.0F)
            .build();

}
