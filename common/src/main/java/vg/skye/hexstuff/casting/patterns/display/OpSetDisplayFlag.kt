package vg.skye.hexstuff.casting.patterns.display

import at.petrak.hexcasting.api.spell.ConstMediaAction
import at.petrak.hexcasting.api.spell.casting.CastingContext
import at.petrak.hexcasting.api.spell.getBool
import at.petrak.hexcasting.api.spell.iota.Iota
import net.minecraft.network.chat.Style
import vg.skye.hexstuff.casting.getDisplay
import vg.skye.hexstuff.casting.iota.DisplayIota

class OpSetDisplayFlag(val flag: (Style, Boolean) -> Style) : ConstMediaAction {
    override val argc = 2

    override fun execute(args: List<Iota>, ctx: CastingContext): List<Iota> {
        val str = args.getDisplay(0, argc)
        val set = args.getBool(1, argc)
        return listOf(DisplayIota(str.copy().withStyle {
            flag(it, set)
        }))
    }
}