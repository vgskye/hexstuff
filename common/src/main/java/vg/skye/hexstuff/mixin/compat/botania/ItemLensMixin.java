package vg.skye.hexstuff.mixin.compat.botania;

import at.petrak.hexcasting.common.items.ItemLens;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import vazkii.botania.api.item.PhantomInkable;
import vazkii.botania.common.helper.ItemNBTHelper;

import java.util.List;

@Mixin(ItemLens.class)
public abstract class ItemLensMixin extends Item implements PhantomInkable {
    public ItemLensMixin(Properties properties) {
        super(properties);
        throw new RuntimeException();
    }

    @Unique
    @Override
    public void appendHoverText(ItemStack stack, Level world, List<Component> tooltip, TooltipFlag flags) {
        if (hasPhantomInk(stack)) {
            tooltip.add(Component.translatable("botaniamisc.hasPhantomInk").withStyle(ChatFormatting.AQUA));
        }
    }

    @Unique
    @Override
    public boolean hasPhantomInk(ItemStack stack) {
        return ItemNBTHelper.getBoolean(stack, "phantomInk", false);
    }

    @Unique
    @Override
    public void setPhantomInk(ItemStack stack, boolean ink) {
        ItemNBTHelper.setBoolean(stack, "phantomInk", ink);
    }
}