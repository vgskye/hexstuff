package vg.skye.hexstuff.fabric.mixin.compat.botania;

import at.petrak.hexcasting.fabric.interop.trinkets.LensTrinketRenderer;
import com.mojang.blaze3d.vertex.PoseStack;
import dev.emi.trinkets.api.SlotReference;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import vazkii.botania.api.item.PhantomInkable;

@Mixin(LensTrinketRenderer.class)
public class LensTrinketRendererMixin {
    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    private void phantomInkLens(ItemStack stack, SlotReference slotReference, EntityModel<? extends LivingEntity> model, PoseStack matrices, MultiBufferSource multiBufferSource, int light, LivingEntity entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch, CallbackInfo ci) {
        if (stack.getItem() instanceof PhantomInkable item && item.hasPhantomInk(stack)) {
            ci.cancel();
        }
    }
}
