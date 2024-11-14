package vg.skye.hexstuff;

import at.petrak.hexcasting.api.spell.mishaps.Mishap;
import net.minecraft.world.item.ItemStack;

public interface ItemStackableIota {
    ItemStack hexstuff$getItemStack() throws Mishap;
}
