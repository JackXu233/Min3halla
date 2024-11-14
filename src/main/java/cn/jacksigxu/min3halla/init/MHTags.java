package cn.jacksigxu.min3halla.init;

import cn.jacksigxu.min3halla.MHMod;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class MHTags {

    public static class Blocks {

        private static TagKey<Block> tag(String name) {
            return BlockTags.create(MHMod.loc(name));
        }
    }

    public static class Items {

        public static final TagKey<Item> FLAVOR_SWEET = tag("flavor/sweet");
        public static final TagKey<Item> FLAVOR_SPICY = tag("flavor/spicy");
        public static final TagKey<Item> FLAVOR_SOUR = tag("flavor/sour");
        public static final TagKey<Item> FLAVOR_BITTER = tag("flavor/bitter");
        public static final TagKey<Item> FLAVOR_BUBBLY = tag("flavor/bubbly");

        public static final TagKey<Item> TYPE_CLASSIC = tag("type/classic");
        public static final TagKey<Item> TYPE_CLASSY = tag("type/classy");
        public static final TagKey<Item> TYPE_GIRLY = tag("type/girly");
        public static final TagKey<Item> TYPE_MANLY = tag("type/manly");
        public static final TagKey<Item> TYPE_PROMO = tag("type/promo");

        public static final TagKey<Item> SECONDARY_BLAND = tag("secondary/bland");
        public static final TagKey<Item> SECONDARY_BURNING = tag("secondary/burning");
        public static final TagKey<Item> SECONDARY_HAPPY = tag("secondary/happy");
        public static final TagKey<Item> SECONDARY_SOBERING = tag("secondary/sobering");
        public static final TagKey<Item> SECONDARY_SOFT = tag("secondary/soft");
        public static final TagKey<Item> SECONDARY_STRONG = tag("secondary/strong");
        public static final TagKey<Item> SECONDARY_VINTAGE = tag("secondary/vintage");

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(MHMod.loc(name));
        }
    }

}
