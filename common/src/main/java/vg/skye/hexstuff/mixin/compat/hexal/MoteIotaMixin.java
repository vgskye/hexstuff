package vg.skye.hexstuff.mixin.compat.hexal;

import at.petrak.hexcasting.api.spell.mishaps.Mishap;
import at.petrak.hexcasting.api.spell.mishaps.MishapInvalidIota;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import ram.talia.hexal.api.spell.iota.MoteIota;
import vg.skye.hexstuff.ItemStackableIota;

@Mixin(value = MoteIota.class, remap = false)
public abstract class MoteIotaMixin implements ItemStackableIota {
    @Shadow public abstract @Nullable MoteIota selfOrNull();

    @Unique
    @Override
    public ItemStack hexstuff$getItemStack() throws Mishap {
        var mote = this.selfOrNull();
        if (mote == null)
            throw MishapInvalidIota.of((MoteIota) (Object) this, 0, "itementityitemframeitemdisplay");
        var stack = new ItemStack(mote.getItem(), (int) mote.getCount());
        stack.setTag(mote.getTag());
        return stack;
    }
}
