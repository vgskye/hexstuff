package vg.skye.hexstuff.casting.iota;

import at.petrak.hexcasting.api.spell.iota.Iota;
import at.petrak.hexcasting.api.spell.iota.IotaType;
import at.petrak.hexcasting.api.utils.HexUtils;
import com.google.re2j.Pattern;
import com.google.re2j.PatternSyntaxException;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.IntTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import org.jetbrains.annotations.NotNull;
import vg.skye.hexstuff.registry.HexStuffIotaTypeRegistry;

import java.util.Objects;

public class RegexIota extends Iota {
    public RegexIota(Pattern pattern) {
        super(HexStuffIotaTypeRegistry.REGEX, pattern);
    }

    @Override
    public boolean isTruthy() {
        return true;
    }

    @Override
    protected boolean toleratesOther(Iota that) {
        return typesMatch(this, that)
                && that instanceof RegexIota rthat
                && this.getPattern().equals(rthat.getPattern());
    }

    public Pattern getPattern() {
        return (Pattern) this.payload;
    }

    @Override
    public @NotNull Tag serialize() {
        var pat = getPattern();
        var tag = new CompoundTag();
        tag.putString("pattern", pat.pattern());
        tag.putInt("flags", pat.flags());
        return tag;
    }


    public static IotaType<RegexIota> TYPE = new IotaType<>() {
        @Override
        public RegexIota deserialize(Tag tag, ServerLevel world) throws IllegalArgumentException {
            var ctag = HexUtils.downcast(tag, CompoundTag.TYPE);
            try {
                var pat = HexUtils.downcast(Objects.requireNonNull(ctag.get("pattern")), StringTag.TYPE);
                var flags = HexUtils.downcast(Objects.requireNonNull(ctag.get("flags")), IntTag.TYPE);

                return new RegexIota(Pattern.compile(pat.getAsString(), flags.getAsInt()));
            } catch (PatternSyntaxException | NullPointerException e) {
                throw new IllegalArgumentException(e);
            }
        }

        @Override
        public Component display(Tag tag) {
            try {
                var ctag = HexUtils.downcast(tag, CompoundTag.TYPE);
                var pat = HexUtils.downcast(Objects.requireNonNull(ctag.get("pattern")), StringTag.TYPE).getAsString();
                var flags = HexUtils.downcast(Objects.requireNonNull(ctag.get("flags")), IntTag.TYPE).getAsInt();
                return Component.literal(String.format("/%s/%s", pat, parseFlags(flags)))
                        .withStyle(ChatFormatting.DARK_AQUA);
            } catch (IllegalArgumentException | NullPointerException e) {
                return Component.translatable("hexcasting.spelldata.unknown");
            }
        }

        @Override
        public int color() {
            return 0xff_00aaaa;
        }

        private static String parseFlags(int flags) {
            var stringBuilder = new StringBuilder();
            if ((flags & Pattern.CASE_INSENSITIVE) != 0)
                stringBuilder.append("i");
            if ((flags & Pattern.MULTILINE) != 0)
                stringBuilder.append("m");
            if ((flags & Pattern.DOTALL) != 0)
                stringBuilder.append("s");
            return stringBuilder.toString();
        }
    };
}
