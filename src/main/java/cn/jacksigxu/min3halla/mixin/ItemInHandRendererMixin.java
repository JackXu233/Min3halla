package cn.jacksigxu.min3halla.mixin;

import cn.jacksigxu.min3halla.item.DrinkItem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.ItemInHandRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemInHandRenderer.class)
public class ItemInHandRendererMixin {

    @Shadow
    @Final
    private ItemRenderer itemRenderer;

    @SuppressWarnings({"ConstantConditions"})
    @Inject(method = "renderItem(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/ItemDisplayContext;ZLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V",
            at = @At(value = "HEAD"), cancellable = true)
    private void renderArmWithItemHead(LivingEntity pEntity, ItemStack pItemStack, ItemDisplayContext pDisplayContext, boolean pLeftHand, PoseStack pPoseStack, MultiBufferSource pBuffer, int pSeed, CallbackInfo ci) {
        if (!(pItemStack.getItem() instanceof DrinkItem)) return;
        if (pItemStack.getTag() == null || !pItemStack.getTag().contains("Big") || !pItemStack.getTag().getBoolean("Big"))
            return;

        ci.cancel();

        pPoseStack.pushPose();
        pPoseStack.scale(1.5f, 1.5f, 1.5f);
        if (!pItemStack.isEmpty()) {
            this.itemRenderer.renderStatic(pEntity, pItemStack, pDisplayContext, pLeftHand, pPoseStack, pBuffer, pEntity.level(), pSeed, OverlayTexture.NO_OVERLAY, pEntity.getId() + pDisplayContext.ordinal());
        }
        pPoseStack.popPose();
    }

}
