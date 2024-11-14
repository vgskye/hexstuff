package vg.skye.hexstuff.casting.patterns.display

import at.petrak.hexcasting.api.spell.ConstMediaAction
import at.petrak.hexcasting.api.spell.casting.CastingContext
import at.petrak.hexcasting.api.spell.iota.EntityIota
import at.petrak.hexcasting.api.spell.iota.Iota
import at.petrak.hexcasting.api.spell.mishaps.MishapEntityTooFarAway
import at.petrak.hexcasting.api.spell.mishaps.MishapInvalidIota
import net.minecraft.network.chat.HoverEvent
import net.minecraft.world.entity.decoration.ItemFrame
import net.minecraft.world.entity.item.ItemEntity
import vg.skye.hexstuff.ItemStackableIota
import vg.skye.hexstuff.casting.getDisplay
import vg.skye.hexstuff.casting.iota.DisplayIota

class OpSetTooltip : ConstMediaAction {
    override val argc = 2

    override fun execute(args: List<Iota>, ctx: CastingContext): List<Iota> {
        val main = args.getDisplay(0, argc)
        val hover = when (val arg = args[1]) {
            is DisplayIota -> HoverEvent(HoverEvent.Action.SHOW_TEXT, arg.component)
            is ItemStackableIota -> {
                HoverEvent(HoverEvent.Action.SHOW_ITEM, HoverEvent.ItemStackInfo(arg.`hexstuff$getItemStack`()))
            }
            is EntityIota -> {
                if (!ctx.isEntityInRange(arg.entity))
                    throw MishapEntityTooFarAway(arg.entity)
                HoverEvent(HoverEvent.Action.SHOW_ITEM, HoverEvent.ItemStackInfo(when (val entity = arg.entity) {
                    is ItemEntity -> entity.item
                    is ItemFrame -> entity.item
                    else -> throw MishapInvalidIota.of(arg, 0, "itementityitemframeitemdisplay")
                }))
            }
            else -> throw MishapInvalidIota.of(arg, 0, "itementityitemframeitemdisplay")
        }
        return listOf(DisplayIota(main.copy().withStyle {
            it.withHoverEvent(hover)
        }))
    }
}