package vg.skye.hexstuff.casting

import at.petrak.hexcasting.api.spell.iota.Iota
import at.petrak.hexcasting.api.spell.mishaps.MishapInvalidIota
import at.petrak.hexcasting.api.spell.mishaps.MishapNotEnoughArgs
import com.google.re2j.Pattern
import net.minecraft.network.chat.Component
import vg.skye.hexstuff.casting.iota.DisplayIota
import vg.skye.hexstuff.casting.iota.RegexIota


fun List<Iota>.getRegex(idx: Int, argc: Int = 0): Pattern {
    val x = this.getOrElse(idx) { throw MishapNotEnoughArgs(idx + 1, this.size) }
    if (x is RegexIota)
        return x.pattern
    throw MishapInvalidIota.ofType(x, if (argc == 0) idx else argc - (idx + 1), "regex")
}


fun List<Iota>.getDisplay(idx: Int, argc: Int = 0): Component {
    val x = this.getOrElse(idx) { throw MishapNotEnoughArgs(idx + 1, this.size) }
    if (x is DisplayIota)
        return x.component
    throw MishapInvalidIota.ofType(x, if (argc == 0) idx else argc - (idx + 1), "display")
}