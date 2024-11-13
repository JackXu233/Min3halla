package cn.jacksigxu.min3halla.gui.screen;

import cn.jacksigxu.min3halla.MHMod;
import cn.jacksigxu.min3halla.gui.menu.DrinkMixerMenu;
import cn.jacksigxu.min3halla.network.MHNetwork;
import cn.jacksigxu.min3halla.network.message.MixMessage;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class DrinkMixerScreen extends AbstractContainerScreen<DrinkMixerMenu> {

    private static final ResourceLocation TEXTURE = MHMod.loc("textures/gui/drink_mixer.png");

    public DrinkMixerScreen(DrinkMixerMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
        imageWidth = 244;
        imageHeight = 203;
    }

    @Override
    protected void renderBg(GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        int i = (this.width - this.imageWidth) / 2;
        int j = (this.height - this.imageHeight) / 2;
        pGuiGraphics.blit(TEXTURE, i, j, 0, 0, this.imageWidth, this.imageHeight);

    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        this.renderBackground(pGuiGraphics);
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        this.renderTooltip(pGuiGraphics, pMouseX, pMouseY);
    }

    @Override
    protected void renderLabels(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY) {

    }

    @Override
    protected void init() {
        super.init();

        int i = (this.width - this.imageWidth) / 2;
        int j = (this.height - this.imageHeight) / 2;

        MixButton mixButton = new MixButton(i + 157, j + 86, Component.literal("Mix"));
        this.addRenderableWidget(mixButton);
    }

    @OnlyIn(Dist.CLIENT)
    class MixButton extends AbstractButton {

        public MixButton(int pX, int pY, Component pMessage) {
            super(pX, pY, 50, 16, pMessage);
        }

        @Override
        protected void renderWidget(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
            if (!DrinkMixerScreen.this.menu.canCraftItem()) {
                pGuiGraphics.blit(TEXTURE, this.getX(), this.getY(), this.isHovered() ? 146 : 44, 204, 50, 16);
            } else if (this.isHovered()) {
                pGuiGraphics.blit(TEXTURE, this.getX(), this.getY(), 95, 204, 50, 16);
            }
        }

        @Override
        public void onPress() {
            MHNetwork.CHANNEL.sendToServer(new MixMessage(0));
        }

        @Override
        protected void updateWidgetNarration(NarrationElementOutput pNarrationElementOutput) {

        }
    }
}
