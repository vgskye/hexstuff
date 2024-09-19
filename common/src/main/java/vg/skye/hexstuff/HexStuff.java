package vg.skye.hexstuff;

import vg.skye.hexstuff.registry.HexStuffIotaTypeRegistry;
import vg.skye.hexstuff.registry.HexStuffItemRegistry;
import vg.skye.hexstuff.registry.HexStuffPatternRegistry;
import vg.skye.hexstuff.networking.HexStuffNetworking;
import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This is effectively the loading entrypoint for most of your code, at least
 * if you are using Architectury as intended.
 */
public class HexStuff {
    public static final String MOD_ID = "hexstuff";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);


    public static void init() {
        LOGGER.info("HexStuff says hello!");

        HexStuffAbstractions.initPlatformSpecific();
        HexStuffItemRegistry.init();
        HexStuffIotaTypeRegistry.init();
        HexStuffPatternRegistry.init();
		HexStuffNetworking.init();

        LOGGER.info(HexStuffAbstractions.getConfigDirectory().toAbsolutePath().normalize().toString());
    }

    /**
     * Shortcut for identifiers specific to this mod.
     */
    public static ResourceLocation id(String string) {
        return new ResourceLocation(MOD_ID, string);
    }
}
