package vg.skye.hexstuff.casting.patterns.regex

import at.petrak.hexcasting.api.spell.ConstMediaAction
import at.petrak.hexcasting.api.spell.casting.CastingContext
import at.petrak.hexcasting.api.spell.iota.Iota
import at.petrak.hexcasting.api.spell.mishaps.MishapInvalidIota
import com.google.re2j.Pattern
import com.google.re2j.PatternSyntaxException
import ram.talia.moreiotas.api.getString
import vg.skye.hexstuff.casting.iota.RegexIota

class OpMakeRegex : ConstMediaAction {
    override val argc = 1

    override fun execute(args: List<Iota>, ctx: CastingContext): List<Iota> {
        val pat = args.getString(0, argc)
        try {
            return listOf(RegexIota(Pattern.compile(pat)))
        } catch (e: PatternSyntaxException) {
            throw MishapInvalidIota.of(args[0], 0, "invalid_regex")
        }
    }
}