package vg.skye.hexstuff.forge.mixin.compat.botania;

import at.petrak.hexcasting.forge.interop.curios.LensCurioRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import top.theillusivec4.curios.api.SlotContext;
import vazkii.botania.api.item.PhantomInkable;

@Mixin(LensCurioRenderer.class)
public class LensCurioRendererMixin {
    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    private void phantomInkLens(ItemStack stack, SlotContext slotContext, PoseStack matrixStack, RenderLayerParent<?, ?> renderLayerParent, MultiBufferSource renderTypeBuffer, int light, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, CallbackInfo ci) {
        if (stack.getItem() instanceof PhantomInkable item && item.hasPhantomInk(stack)) {
            ci.cancel();
        }
    }
}
