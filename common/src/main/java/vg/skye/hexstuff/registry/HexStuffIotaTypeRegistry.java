package vg.skye.hexstuff.registry;

import at.petrak.hexcasting.api.spell.iota.Iota;
import at.petrak.hexcasting.api.spell.iota.IotaType;
import at.petrak.hexcasting.common.lib.hex.HexIotaTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.Registry;
import vg.skye.hexstuff.casting.iota.DisplayIota;
import vg.skye.hexstuff.casting.iota.RegexIota;

import java.util.HashMap;
import java.util.Map;

import static vg.skye.hexstuff.HexStuff.id;

public class HexStuffIotaTypeRegistry {
    public static Map<ResourceLocation, IotaType<?>> TYPES = new HashMap<>();

    public static IotaType<RegexIota> REGEX = register("regex", RegexIota.TYPE);
    public static IotaType<DisplayIota> DISPLAY = register("display", DisplayIota.TYPE);

    public static void init() {
        for (Map.Entry<ResourceLocation, IotaType<?>> entry : TYPES.entrySet()) {
            Registry.register(HexIotaTypes.REGISTRY, entry.getKey(), entry.getValue());
        }
    }

    private static <U extends Iota, T extends IotaType<U>> T register(String name, T type) {
        IotaType<?> old = TYPES.put(id(name), type);
        if (old != null) {
            throw new IllegalArgumentException("Typo? Duplicate id " + name);
        }
        return type;
    }
}
