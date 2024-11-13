package cn.jacksigxu.min3halla.client.tooltip;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ClientImageTooltip implements ClientTooltipComponent {

    private final int width;
    private final int height;
    private final List<Component> tooltips;
    private final ItemStack stack;

    @Override
    public void renderImage(@NotNull Font font, int x, int y, GuiGraphics guiGraphics) {
        guiGraphics.pose().pushPose();
        guiGraphics.renderItem(stack, x, y + 2);

        if (!tooltips.isEmpty()) {
            guiGraphics.drawString(font, tooltips.get(0), x + 20, y, 5636095);
        }
        if (tooltips.size() > 1) {
            guiGraphics.drawString(font, tooltips.get(1), x + 20, y + 10, 0xFFFFFF);
        }

        guiGraphics.pose().popPose();
    }

    public ClientImageTooltip(ImageTooltip tooltip) {
        this.width = tooltip.width();
        this.height = tooltip.height();
        this.tooltips = tooltip.tooltips();
        this.stack = tooltip.stack();
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getWidth(@NotNull Font font) {
        return width;
    }


}
