package cn.jacksigxu.min3halla.gui.screen;

import cn.jacksigxu.min3halla.MHMod;
import cn.jacksigxu.min3halla.block.entity.FermentBarrelBlockEntity;
import cn.jacksigxu.min3halla.gui.menu.FermentBarrelMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class FermentBarrelScreen extends AbstractContainerScreen<FermentBarrelMenu> {

    private static final ResourceLocation TEXTURE = MHMod.loc("textures/gui/ferment_barrel.png");

    public FermentBarrelScreen(FermentBarrelMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
        imageWidth = 176;
        imageHeight = 165;
    }

    @Override
    protected void renderBg(GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        int i = (this.width - this.imageWidth) / 2;
        int j = (this.height - this.imageHeight) / 2;
        pGuiGraphics.blit(TEXTURE, i, j, 0, 0, this.imageWidth, this.imageHeight);

        float progress = FermentBarrelScreen.this.menu.getProgress();
        pGuiGraphics.blit(TEXTURE, i + 73, j + 33, 177, 0,
                (int) (progress / FermentBarrelBlockEntity.MAX_PROGRESS * 22f), 16);
    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        this.renderBackground(pGuiGraphics);
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        this.renderTooltip(pGuiGraphics, pMouseX, pMouseY);
    }

    @Override
    protected void init() {
        super.init();
        this.titleLabelX = 8;
        this.titleLabelY = 4;
        this.inventoryLabelX = 8;
        this.inventoryLabelY = 72;
    }
}
