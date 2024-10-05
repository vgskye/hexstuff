package vg.skye.hexstuff.casting.patterns.display

import at.petrak.hexcasting.api.spell.ConstMediaAction
import at.petrak.hexcasting.api.spell.casting.CastingContext
import at.petrak.hexcasting.api.spell.getVec3
import at.petrak.hexcasting.api.spell.iota.Iota
import net.minecraft.util.FastColor
import vg.skye.hexstuff.casting.getDisplay
import vg.skye.hexstuff.casting.iota.DisplayIota

class OpSetColor : ConstMediaAction {
    override val argc = 2

    override fun execute(args: List<Iota>, ctx: CastingContext): List<Iota> {
        val str = args.getDisplay(0, argc)
        val color = args.getVec3(1, argc)
        val r = color.x.toInt().coerceAtMost(255).coerceAtLeast(0)
        val g = color.y.toInt().coerceAtMost(255).coerceAtLeast(0)
        val b = color.z.toInt().coerceAtMost(255).coerceAtLeast(0)
        val styled = str.copy().withStyle {
            it.withColor(FastColor.ARGB32.color(0, r, g, b))
        }
        return listOf(DisplayIota(styled))
    }
}