package vg.skye.hexstuff.casting.iota;

import at.petrak.hexcasting.api.spell.iota.Iota;
import at.petrak.hexcasting.api.spell.iota.IotaType;
import at.petrak.hexcasting.api.utils.HexUtils;
import net.minecraft.nbt.StringTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import org.jetbrains.annotations.NotNull;
import vg.skye.hexstuff.registry.HexStuffIotaTypeRegistry;

public class DisplayIota extends Iota {
    public DisplayIota(Component component) {
        super(HexStuffIotaTypeRegistry.DISPLAY, component);
    }

    @Override
    public boolean isTruthy() {
        return !this.getComponent().getString().isEmpty();
    }

    @Override
    protected boolean toleratesOther(Iota that) {
        return typesMatch(this, that)
                && that instanceof DisplayIota dthat
                && this.getComponent().toFlatList().equals(dthat.getComponent().toFlatList());
    }

    public Component getComponent() {
        return (Component) this.payload;
    }

    @Override
    public @NotNull Tag serialize() {
        return StringTag.valueOf(Component.Serializer.toJson(getComponent()));
    }

    public static IotaType<DisplayIota> TYPE = new IotaType<>() {
        @Override
        public DisplayIota deserialize(Tag tag, ServerLevel world) throws IllegalArgumentException {
            var stag = HexUtils.downcast(tag, StringTag.TYPE);
            return new DisplayIota(Component.Serializer.fromJson(stag.getAsString()));
        }

        @Override
        public Component display(Tag tag) {
            try {
                var stag = HexUtils.downcast(tag, StringTag.TYPE);
                return Component.Serializer.fromJson(stag.getAsString());
            } catch (IllegalArgumentException | NullPointerException e) {
                return Component.translatable("hexcasting.spelldata.unknown");
            }
        }

        @Override
        public int color() {
            return 0xff_ffffff;
        }
    };
}
