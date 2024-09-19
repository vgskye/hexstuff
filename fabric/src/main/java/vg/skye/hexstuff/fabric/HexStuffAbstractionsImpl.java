package vg.skye.hexstuff.fabric;

import net.fabricmc.loader.api.FabricLoader;
import vg.skye.hexstuff.HexStuffAbstractions;

import java.nio.file.Path;

public class HexStuffAbstractionsImpl {
    /**
     * This is the actual implementation of {@link HexStuffAbstractions#getConfigDirectory()}.
     */
    public static Path getConfigDirectory() {
        return FabricLoader.getInstance().getConfigDir();
    }
	
    public static void initPlatformSpecific() {
        HexStuffConfigFabric.init();
    }
}
