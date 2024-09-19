package vg.skye.hexstuff.casting.patterns.regex

import at.petrak.hexcasting.api.spell.ConstMediaAction
import at.petrak.hexcasting.api.spell.casting.CastingContext
import at.petrak.hexcasting.api.spell.getBool
import at.petrak.hexcasting.api.spell.iota.Iota
import com.google.re2j.Pattern
import vg.skye.hexstuff.casting.getRegex
import vg.skye.hexstuff.casting.iota.RegexIota

class OpSetRegexFlag(val flag: Int) : ConstMediaAction {
    override val argc = 2

    override fun execute(args: List<Iota>, ctx: CastingContext): List<Iota> {
        val pat = args.getRegex(0, argc)
        val set = args.getBool(1, argc)
        val flags = if (set) {
            pat.flags().or(flag)
        } else {
            pat.flags().and(flag.inv())
        }
        return listOf(RegexIota(Pattern.compile(pat.pattern(), flags)))
    }
}