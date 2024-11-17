package cn.jacksigxu.min3halla.init;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class MHFoodProperties {

    public static final float DEFAULT_PROB = 0.3F;
    public static final int DEFAULT_BENEFICIAL_TICK = 1200;
    public static final int DEFAULT_NEUTRAL_TICK = 600;
    public static final int DEFAULT_HARMFUL_TICK = 300;

    public static final FoodProperties BAD_TOUCH = new FoodProperties.Builder().nutrition(2).saturationMod(0.4F).alwaysEat()
            .effect(() -> new MobEffectInstance(MobEffects.DIG_SPEED, DEFAULT_BENEFICIAL_TICK, 1), DEFAULT_PROB)
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, DEFAULT_HARMFUL_TICK, 0), 0.4F)
            .build();

    public static final FoodProperties BEER = new FoodProperties.Builder().nutrition(2).saturationMod(0.4F).alwaysEat()
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, DEFAULT_BENEFICIAL_TICK, 0), DEFAULT_PROB)
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, DEFAULT_HARMFUL_TICK, 0), 0.4F)
            .build();

    public static final FoodProperties BLEEDING_JANE = new FoodProperties.Builder().nutrition(2).saturationMod(0.4F).alwaysEat()
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, DEFAULT_BENEFICIAL_TICK, 0), DEFAULT_PROB)
            .build();

    public static final FoodProperties BLOOM_LIGHT = new FoodProperties.Builder().nutrition(2).saturationMod(0.4F).alwaysEat()
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, DEFAULT_BENEFICIAL_TICK * 2, 1), DEFAULT_PROB)
            .effect(() -> new MobEffectInstance(MobEffects.GLOWING, DEFAULT_NEUTRAL_TICK * 2, 0), DEFAULT_PROB)
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, DEFAULT_HARMFUL_TICK * 2, 0), 0.3F)
            .build();

    public static final FoodProperties BLUE_FAIRY = new FoodProperties.Builder().nutrition(1).saturationMod(0.4F).alwaysEat()
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, DEFAULT_BENEFICIAL_TICK * 2, 0), DEFAULT_PROB)
            .build();

    public static final FoodProperties BRANDTINI = new FoodProperties.Builder().nutrition(3).saturationMod(0.4F).alwaysEat()
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, DEFAULT_BENEFICIAL_TICK * 2, 0), DEFAULT_PROB)
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, DEFAULT_HARMFUL_TICK * 2, 0), 0.1F)
            .build();

    public static final FoodProperties COBALT_VELVET = new FoodProperties.Builder().nutrition(1).saturationMod(0.4F).alwaysEat()
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, DEFAULT_BENEFICIAL_TICK, 1), DEFAULT_PROB)
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, DEFAULT_HARMFUL_TICK, 0), 0.5F)
            .build();

    public static final FoodProperties CREVICE_SPIKE = new FoodProperties.Builder().nutrition(2).saturationMod(0.4F).alwaysEat()
            .effect(() -> new MobEffectInstance(MobEffects.DIG_SPEED, DEFAULT_BENEFICIAL_TICK, 0), DEFAULT_PROB)
            .build();

    public static final FoodProperties FLAMING_MOAI = new FoodProperties.Builder().nutrition(4).saturationMod(0.4F).alwaysEat()
            .effect(() -> new MobEffectInstance(MobEffects.DIG_SPEED, DEFAULT_BENEFICIAL_TICK, 0), DEFAULT_PROB * 2)
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, DEFAULT_HARMFUL_TICK, 0), 0.42F)
            .build();

    public static final FoodProperties FLUFFY_DREAM = new FoodProperties.Builder().nutrition(2).saturationMod(0.4F).alwaysEat()
            .effect(() -> new MobEffectInstance(MobEffects.DIG_SPEED, DEFAULT_BENEFICIAL_TICK * 2, 0), DEFAULT_PROB)
            .build();

    public static final FoodProperties FRINGE_WEAVER = new FoodProperties.Builder().nutrition(1).saturationMod(0.4F).alwaysEat()
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, DEFAULT_BENEFICIAL_TICK * 2, 0), DEFAULT_PROB)
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, DEFAULT_HARMFUL_TICK, 0), 0.9F)
            .build();

    public static final FoodProperties FROTHY_WATER = new FoodProperties.Builder().nutrition(1).saturationMod(0.4F).alwaysEat()
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, DEFAULT_BENEFICIAL_TICK * 2, 0), DEFAULT_PROB)
            .build();

    public static final FoodProperties GRIZZLY_TEMPLE = new FoodProperties.Builder().nutrition(3).saturationMod(0.4F).alwaysEat()
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, DEFAULT_BENEFICIAL_TICK, 0), DEFAULT_PROB)
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, DEFAULT_HARMFUL_TICK, 0), 0.1F)
            .build();

    public static final FoodProperties GUT_PUNCH = new FoodProperties.Builder().nutrition(2).saturationMod(0.4F).alwaysEat()
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, DEFAULT_BENEFICIAL_TICK * 2, 0), DEFAULT_PROB)
            .build();

    public static final FoodProperties MARSBLAST = new FoodProperties.Builder().nutrition(7).saturationMod(0.4F).alwaysEat()
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, DEFAULT_BENEFICIAL_TICK, 0), DEFAULT_PROB * 2)
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, DEFAULT_HARMFUL_TICK, 0), 0.3F)
            .build();

    public static final FoodProperties MERCURYBLAST = new FoodProperties.Builder().nutrition(2).saturationMod(0.4F).alwaysEat()
            .effect(() -> new MobEffectInstance(MobEffects.DIG_SPEED, DEFAULT_BENEFICIAL_TICK, 1), DEFAULT_PROB)
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, DEFAULT_HARMFUL_TICK, 0), 0.2F)
            .build();

    public static final FoodProperties MOONBLAST = new FoodProperties.Builder().nutrition(2).saturationMod(0.4F).alwaysEat()
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, DEFAULT_BENEFICIAL_TICK, 1), DEFAULT_PROB)
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, DEFAULT_HARMFUL_TICK, 0), 0.2F)
            .build();

    public static final FoodProperties PIANO_MAN = new FoodProperties.Builder().nutrition(10).saturationMod(0.4F).alwaysEat()
            .effect(() -> new MobEffectInstance(MobEffects.DIG_SPEED, DEFAULT_BENEFICIAL_TICK, 1), DEFAULT_PROB * 2)
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, DEFAULT_HARMFUL_TICK, 0), 0.3F)
            .build();

    public static final FoodProperties PIANO_WOMAN = new FoodProperties.Builder().nutrition(10).saturationMod(0.4F).alwaysEat()
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, DEFAULT_BENEFICIAL_TICK * 2, 0), DEFAULT_PROB * 2)
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, DEFAULT_HARMFUL_TICK, 0), 0.3F)
            .build();

    public static final FoodProperties PILE_DRIVER = new FoodProperties.Builder().nutrition(2).saturationMod(0.4F).alwaysEat()
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, DEFAULT_BENEFICIAL_TICK, 0), DEFAULT_PROB)
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, DEFAULT_HARMFUL_TICK, 0), 0.4F)
            .build();

    public static final FoodProperties SPARKLE_STAR = new FoodProperties.Builder().nutrition(1).saturationMod(0.4F).alwaysEat()
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, DEFAULT_BENEFICIAL_TICK * 2, 0), DEFAULT_PROB)
            .build();

    public static final FoodProperties SUGAR_RUSH = new FoodProperties.Builder().nutrition(1).saturationMod(0.4F).alwaysEat()
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, DEFAULT_BENEFICIAL_TICK, 0), DEFAULT_PROB)
            .build();

    public static final FoodProperties SUNSHINE_CLOUD = new FoodProperties.Builder().nutrition(1).saturationMod(0.4F).alwaysEat()
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, DEFAULT_BENEFICIAL_TICK, 1), DEFAULT_PROB)
            .build();

    public static final FoodProperties SUPLEX = new FoodProperties.Builder().nutrition(2).saturationMod(0.4F).alwaysEat()
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, DEFAULT_BENEFICIAL_TICK, 1), DEFAULT_PROB)
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, DEFAULT_HARMFUL_TICK, 0), 0.3F)
            .build();

    public static final FoodProperties ZEN_STAR = new FoodProperties.Builder().nutrition(10).saturationMod(0.4F).alwaysEat()
            .effect(() -> new MobEffectInstance(MobEffects.DIG_SPEED, DEFAULT_BENEFICIAL_TICK, 1), DEFAULT_PROB * 2)
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, DEFAULT_HARMFUL_TICK, 0), 0.4F)
            .build();

    public static final FoodProperties MULAN_TEA = new FoodProperties.Builder().nutrition(3).saturationMod(0.4F).alwaysEat()
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, DEFAULT_BENEFICIAL_TICK, 0), DEFAULT_PROB)
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, DEFAULT_HARMFUL_TICK, 0), 0.5F)
            .build();

    public static final FoodProperties A_FEDORA = new FoodProperties.Builder().nutrition(3).saturationMod(0.4F).alwaysEat()
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, DEFAULT_BENEFICIAL_TICK, 0), DEFAULT_PROB)
            .build();

    public static final FoodProperties RUM = new FoodProperties.Builder().nutrition(3).saturationMod(0.4F).alwaysEat()
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, DEFAULT_BENEFICIAL_TICK, 0), DEFAULT_PROB)
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, DEFAULT_HARMFUL_TICK, 0), 0.6F)
            .build();

    public static final FoodProperties ABSINTHE = new FoodProperties.Builder().nutrition(3).saturationMod(0.4F).alwaysEat()
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, DEFAULT_BENEFICIAL_TICK, 0), DEFAULT_PROB)
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, DEFAULT_HARMFUL_TICK, 0), 0.75F)
            .build();

    public static final FoodProperties ERROR_DRINK = new FoodProperties.Builder().nutrition(1).saturationMod(0.4F).alwaysEat()
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, DEFAULT_HARMFUL_TICK, 0), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.HUNGER, DEFAULT_HARMFUL_TICK, 0), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.WEAKNESS, DEFAULT_HARMFUL_TICK, 0), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.POISON, DEFAULT_HARMFUL_TICK, 0), 1.0F)
            .build();

    public static final FoodProperties HYPER_ACID_X = new FoodProperties.Builder().nutrition(0).saturationMod(0.0F).alwaysEat()
            .effect(() -> new MobEffectInstance(MobEffects.DIG_SPEED, DEFAULT_BENEFICIAL_TICK * 2, 1), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.HUNGER, DEFAULT_HARMFUL_TICK * 2, 0), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, DEFAULT_HARMFUL_TICK * 2, 0), 1.0F)
            .build();

    public static final FoodProperties GLITCH_LIQUID = new FoodProperties.Builder().nutrition(0).saturationMod(0.0F).alwaysEat()
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, DEFAULT_BENEFICIAL_TICK * 2, 1), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.BLINDNESS, DEFAULT_HARMFUL_TICK * 2, 0), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.DARKNESS, DEFAULT_HARMFUL_TICK * 2, 0), 1.0F)
            .build();

    public static final FoodProperties SUPERB_BASKETBALL = new FoodProperties.Builder().nutrition(13).saturationMod(0.4F).alwaysEat()
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, DEFAULT_BENEFICIAL_TICK * 2, 1), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.JUMP, DEFAULT_BENEFICIAL_TICK * 2, 1), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, DEFAULT_HARMFUL_TICK * 2, 0), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.HUNGER, DEFAULT_HARMFUL_TICK * 2, 1), 1.0F)
            .build();

    public static final FoodProperties SEVEN_WOLVES = new FoodProperties.Builder().nutrition(7).saturationMod(0.4F).alwaysEat()
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, DEFAULT_BENEFICIAL_TICK, 3), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, DEFAULT_BENEFICIAL_TICK, 0), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, DEFAULT_HARMFUL_TICK, 0), 0.2F)
            .build();

    public static final FoodProperties ALARM_LAMP = new FoodProperties.Builder().nutrition(12).saturationMod(0.25F).alwaysEat()
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, DEFAULT_BENEFICIAL_TICK, 0), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, DEFAULT_BENEFICIAL_TICK, 0), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, DEFAULT_HARMFUL_TICK, 0), 0.4F)
            .build();

    public static final FoodProperties AFTERGLOW = new FoodProperties.Builder().nutrition(6).saturationMod(0.4F).alwaysEat()
            .effect(() -> new MobEffectInstance(MobEffects.DIG_SPEED, DEFAULT_BENEFICIAL_TICK, 0), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.JUMP, DEFAULT_BENEFICIAL_TICK, 0), 1.0F)
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, DEFAULT_HARMFUL_TICK, 0), 0.25F)
            .build();

}
