package vg.skye.hexstuff.casting.patterns.display

import at.petrak.hexcasting.api.spell.ConstMediaAction
import at.petrak.hexcasting.api.spell.casting.CastingContext
import at.petrak.hexcasting.api.spell.iota.Iota
import net.minecraft.network.chat.Component
import vg.skye.hexstuff.casting.getDisplay
import vg.skye.hexstuff.casting.iota.DisplayIota

class OpConcatDisplay : ConstMediaAction {
    override val argc = 2

    override fun execute(args: List<Iota>, ctx: CastingContext): List<Iota> {
        val lhs = args.getDisplay(0, argc)
        val rhs = args.getDisplay(1, argc)
        return listOf(DisplayIota(Component.empty().append(lhs).append(rhs)))
    }
}