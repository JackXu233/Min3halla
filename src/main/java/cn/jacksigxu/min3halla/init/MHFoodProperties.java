package cn.jacksigxu.min3halla.init;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class MHFoodProperties {

    public static final FoodProperties BAD_TOUCH = new FoodProperties.Builder().nutrition(5).saturationMod(0.4F).alwaysEat().build();
    public static final FoodProperties BEER = new FoodProperties.Builder().nutrition(5).saturationMod(0.4F).alwaysEat().build();
    public static final FoodProperties BLEEDING_JANE = new FoodProperties.Builder().nutrition(5).saturationMod(0.4F).alwaysEat().build();
    public static final FoodProperties BLOOM_LIGHT = new FoodProperties.Builder().nutrition(5).saturationMod(0.4F).alwaysEat().build();
    public static final FoodProperties BLUE_FAIRY = new FoodProperties.Builder().nutrition(5).saturationMod(0.4F).alwaysEat().build();
    public static final FoodProperties BRANDTINI = new FoodProperties.Builder().nutrition(5).saturationMod(0.4F).alwaysEat().build();

    public static final FoodProperties SUGAR_RUSH = new FoodProperties.Builder().nutrition(6).saturationMod(0.4F).alwaysEat().build();

    public static final FoodProperties ZEN_STAR = new FoodProperties.Builder().nutrition(5).saturationMod(0.4F).alwaysEat().build();

    public static final FoodProperties RUM = new FoodProperties.Builder().nutrition(5).saturationMod(0.4F).alwaysEat()
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 100, 0), 0.3F)
            .build();

    public static final FoodProperties ERROR_DRINK = new FoodProperties.Builder().nutrition(1).saturationMod(0.2F).alwaysEat().build();

}
