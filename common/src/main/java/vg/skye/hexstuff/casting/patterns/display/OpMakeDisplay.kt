package vg.skye.hexstuff.casting.patterns.display

import at.petrak.hexcasting.api.spell.ConstMediaAction
import at.petrak.hexcasting.api.spell.casting.CastingContext
import at.petrak.hexcasting.api.spell.iota.Iota
import net.minecraft.network.chat.Component
import ram.talia.moreiotas.api.getString
import vg.skye.hexstuff.casting.iota.DisplayIota

class OpMakeDisplay : ConstMediaAction {
    override val argc = 1

    override fun execute(args: List<Iota>, ctx: CastingContext): List<Iota> {
        val str = args.getString(0, argc)
        return listOf(DisplayIota(Component.literal(str)))
    }
}