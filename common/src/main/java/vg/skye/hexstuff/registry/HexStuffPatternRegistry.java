package vg.skye.hexstuff.registry;

import at.petrak.hexcasting.api.PatternRegistry;
import at.petrak.hexcasting.api.spell.Action;
import at.petrak.hexcasting.api.spell.math.HexDir;
import at.petrak.hexcasting.api.spell.math.HexPattern;
import com.google.re2j.Pattern;
import kotlin.Triple;
import vg.skye.hexstuff.casting.patterns.regex.OpMakeRegex;
import vg.skye.hexstuff.casting.patterns.regex.OpMatchRegex;
import vg.skye.hexstuff.casting.patterns.regex.OpSetRegexFlag;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.List;

import static vg.skye.hexstuff.HexStuff.id;

public class HexStuffPatternRegistry {
    public static List<Triple<HexPattern, ResourceLocation, Action>> PATTERNS = new ArrayList<>();
    public static List<Triple<HexPattern, ResourceLocation, Action>> PER_WORLD_PATTERNS = new ArrayList<>();
    // IMPORTANT: be careful to keep the registration calls looking like this, or you'll have to edit the hexdoc pattern regex.
    public static HexPattern MAKE_REGEX = register(HexPattern.fromAngles("qwawqwaqwdda", HexDir.EAST), "regex/make", new OpMakeRegex());
    public static HexPattern MATCH_REGEX = register(HexPattern.fromAngles("qwawqwaqw", HexDir.EAST), "regex/match", new OpMatchRegex());
    public static HexPattern FLAG_REGEX_CASE_INSENSITIVE = register(HexPattern.fromAngles("qwawqwaqe", HexDir.EAST), "regex/flag/case_insensitive", new OpSetRegexFlag(Pattern.CASE_INSENSITIVE));
    public static HexPattern FLAG_REGEX_DOTALL = register(HexPattern.fromAngles("qwawqwaqea", HexDir.EAST), "regex/flag/dotall", new OpSetRegexFlag(Pattern.DOTALL));
    public static HexPattern FLAG_REGEX_MULTILINE = register(HexPattern.fromAngles("qwawqwaqeaa", HexDir.EAST), "regex/flag/multiline", new OpSetRegexFlag(Pattern.MULTILINE));

    public static void init() {
        try {
            for (Triple<HexPattern, ResourceLocation, Action> patternTriple : PATTERNS) {
                PatternRegistry.mapPattern(patternTriple.getFirst(), patternTriple.getSecond(), patternTriple.getThird());
            }
            for (Triple<HexPattern, ResourceLocation, Action> patternTriple : PER_WORLD_PATTERNS) {
                PatternRegistry.mapPattern(patternTriple.getFirst(), patternTriple.getSecond(), patternTriple.getThird(), true);
            }
        } catch (PatternRegistry.RegisterPatternException e) {
            e.printStackTrace();
        }
    }

    private static HexPattern register(HexPattern pattern, String name, Action action) {
        Triple<HexPattern, ResourceLocation, Action> triple = new Triple<>(pattern, id(name), action);
        PATTERNS.add(triple);
        return pattern;
    }

    private static HexPattern registerPerWorld(HexPattern pattern, String name, Action action) {
        Triple<HexPattern, ResourceLocation, Action> triple = new Triple<>(pattern, id(name), action);
        PER_WORLD_PATTERNS.add(triple);
        return pattern;
    }
}
