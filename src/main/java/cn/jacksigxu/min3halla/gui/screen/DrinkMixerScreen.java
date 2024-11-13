package cn.jacksigxu.min3halla.gui.screen;

import cn.jacksigxu.min3halla.MHMod;
import cn.jacksigxu.min3halla.gui.menu.DrinkMixerMenu;
import cn.jacksigxu.min3halla.network.MHNetwork;
import cn.jacksigxu.min3halla.network.message.IngredientAdjustMessage;
import cn.jacksigxu.min3halla.network.message.IngredientSelectMessage;
import cn.jacksigxu.min3halla.network.message.MixMessage;
import cn.jacksigxu.min3halla.network.message.ResetMessage;
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

        // 五种原料
        int adeCount = DrinkMixerScreen.this.menu.getIngredientCount(0);
        renderIngredients(pGuiGraphics, i + 58, j + 19, 0, 204, adeCount);
        int bexCount = DrinkMixerScreen.this.menu.getIngredientCount(1);
        renderIngredients(pGuiGraphics, i + 118, j + 19, 5, 204, bexCount);
        int pwdCount = DrinkMixerScreen.this.menu.getIngredientCount(2);
        renderIngredients(pGuiGraphics, i + 178, j + 19, 10, 204, pwdCount);
        int flaCount = DrinkMixerScreen.this.menu.getIngredientCount(3);
        renderIngredients(pGuiGraphics, i + 58, j + 57, 15, 204, flaCount);
        int karCount = DrinkMixerScreen.this.menu.getIngredientCount(4);
        renderIngredients(pGuiGraphics, i + 178, j + 57, 20, 204, karCount);

        // 底部方块
        int sum = adeCount + bexCount + pwdCount + flaCount + karCount;
        renderBlocksBelow(pGuiGraphics, i + 100, j + 89, sum);
    }

    private void renderIngredients(GuiGraphics pGuiGraphics, int x, int y, int u, int v, int count) {
        for (int k = 0; k < Math.min(5, count); k++) {
            pGuiGraphics.blit(TEXTURE, x + k * 5, y, u, v, 4, 9);
        }

        for (int k = 0; k < Math.min(5, count - 5); k++) {
            pGuiGraphics.blit(TEXTURE, x + k * 5, y + 11, u, v, 4, 9);
        }

        for (int k = 0; k < Math.min(5, count - 10); k++) {
            pGuiGraphics.blit(TEXTURE, x + k * 5, y, u, v + 9, 4, 9);
        }

        for (int k = 0; k < Math.min(5, count - 15); k++) {
            pGuiGraphics.blit(TEXTURE, x + k * 5, y + 11, u, v + 9, 4, 9);
        }
    }

    private void renderBlocksBelow(GuiGraphics pGuiGraphics, int x, int y, int count) {
        for (int k = 0; k < Math.min(5, count); k++) {
            pGuiGraphics.blit(TEXTURE, x + k * 9, y, 0, 222, 8, 5);
        }

        for (int k = 0; k < Math.min(5, count - 5); k++) {
            pGuiGraphics.blit(TEXTURE, x + k * 9, y + 6, 0, 222, 8, 5);
        }

        for (int k = 0; k < Math.min(5, count - 10); k++) {
            pGuiGraphics.blit(TEXTURE, x + k * 9, y, 9, 222, 8, 5);
        }

        for (int k = 0; k < Math.min(5, count - 15); k++) {
            pGuiGraphics.blit(TEXTURE, x + k * 9, y + 6, 9, 222, 8, 5);
        }
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
        ResetButton resetButton = new ResetButton(i + 37, j + 86, Component.literal("Reset"));
        this.addRenderableWidget(resetButton);

        IngredientAdjustButton adeButtonInc = new IngredientAdjustButton(i + 83, j + 21, Component.literal("+"), true, 0);
        IngredientAdjustButton adeButtonDec = new IngredientAdjustButton(i + 83, j + 32, Component.literal("-"), false, 0);
        this.addRenderableWidget(adeButtonInc);
        this.addRenderableWidget(adeButtonDec);

        IngredientAdjustButton bexButtonInc = new IngredientAdjustButton(i + 143, j + 21, Component.literal("+"), true, 1);
        IngredientAdjustButton bexButtonDec = new IngredientAdjustButton(i + 143, j + 32, Component.literal("-"), false, 1);
        this.addRenderableWidget(bexButtonInc);
        this.addRenderableWidget(bexButtonDec);

        IngredientAdjustButton pwdButtonInc = new IngredientAdjustButton(i + 203, j + 21, Component.literal("+"), true, 2);
        IngredientAdjustButton pwdButtonDec = new IngredientAdjustButton(i + 203, j + 32, Component.literal("-"), false, 2);
        this.addRenderableWidget(pwdButtonInc);
        this.addRenderableWidget(pwdButtonDec);

        IngredientAdjustButton flaButtonInc = new IngredientAdjustButton(i + 83, j + 59, Component.literal("+"), true, 3);
        IngredientAdjustButton flaButtonDec = new IngredientAdjustButton(i + 83, j + 70, Component.literal("-"), false, 3);
        this.addRenderableWidget(flaButtonInc);
        this.addRenderableWidget(flaButtonDec);

        IngredientAdjustButton karButtonInc = new IngredientAdjustButton(i + 203, j + 59, Component.literal("+"), true, 4);
        IngredientAdjustButton karButtonDec = new IngredientAdjustButton(i + 203, j + 70, Component.literal("-"), false, 4);
        this.addRenderableWidget(karButtonInc);
        this.addRenderableWidget(karButtonDec);

        IngredientSelectButton iceButton = new IngredientSelectButton(i + 10, j + 10, Component.literal("Ice"), 5);
        this.addRenderableWidget(iceButton);
        IngredientSelectButton ageButton = new IngredientSelectButton(i + 10, j + 48, Component.literal("Age"), 6);
        this.addRenderableWidget(ageButton);
        IngredientSelectButton dyeButton = new IngredientSelectButton(i + 216, j + 10, Component.literal("Dye"), 7);
        this.addRenderableWidget(dyeButton);
        IngredientSelectButton extraButton = new IngredientSelectButton(i + 216, j + 48, Component.literal("Extra"), 8);
        this.addRenderableWidget(extraButton);
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

    @OnlyIn(Dist.CLIENT)
    static class ResetButton extends AbstractButton {

        public ResetButton(int pX, int pY, Component pMessage) {
            super(pX, pY, 50, 16, pMessage);
        }

        @Override
        protected void renderWidget(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
            if (this.isHovered()) {
                pGuiGraphics.blit(TEXTURE, this.getX(), this.getY(), 44, 221, 50, 16);
            }
        }

        @Override
        public void onPress() {
            MHNetwork.CHANNEL.sendToServer(new ResetMessage(0));
        }

        @Override
        protected void updateWidgetNarration(NarrationElementOutput pNarrationElementOutput) {

        }
    }

    @OnlyIn(Dist.CLIENT)
    static class IngredientAdjustButton extends AbstractButton {

        private final boolean increase;
        private final int index;

        public IngredientAdjustButton(int pX, int pY, Component pMessage, boolean increase, int index) {
            super(pX, pY, 5, 5, pMessage);
            this.increase = increase;
            this.index = index;
        }

        @Override
        protected void renderWidget(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
            if (this.isHovered()) {
                pGuiGraphics.blit(TEXTURE, this.getX(), this.getY(), this.increase ? 25 : 31, 204, 5, 5);
            }
        }

        @Override
        public void onPress() {
            MHNetwork.CHANNEL.sendToServer(new IngredientAdjustMessage(this.index, this.increase));
        }

        @Override
        protected void updateWidgetNarration(NarrationElementOutput pNarrationElementOutput) {

        }
    }

    @OnlyIn(Dist.CLIENT)
    class IngredientSelectButton extends AbstractButton {

        private final int index;

        public IngredientSelectButton(int pX, int pY, Component pMessage, int index) {
            super(pX, pY, 18, 10, pMessage);
            this.index = index;
        }

        @Override
        protected void renderWidget(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
            if (DrinkMixerScreen.this.menu.getIngredientSelected(this.index)) {
                pGuiGraphics.blit(TEXTURE, this.getX(), this.getY(), 25, 221, 18, 10);
            }
            if (this.isHovered()) {
                pGuiGraphics.blit(TEXTURE, this.getX(), this.getY(), 25, 210, 18, 10);
            }
        }

        @Override
        public void onPress() {
            MHNetwork.CHANNEL.sendToServer(new IngredientSelectMessage(this.index));
        }

        @Override
        protected void updateWidgetNarration(NarrationElementOutput pNarrationElementOutput) {

        }
    }
}
