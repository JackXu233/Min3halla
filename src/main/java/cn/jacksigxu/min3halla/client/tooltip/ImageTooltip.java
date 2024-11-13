package cn.jacksigxu.min3halla.client.tooltip;

import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public record ImageTooltip(int width, int height, ItemStack stack, List<Component> tooltips) implements TooltipComponent {
}
