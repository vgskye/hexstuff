package vg.skye.hexstuff.forge;

import vg.skye.hexstuff.HexStuffAbstractions;
import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.Path;

public class HexStuffAbstractionsImpl {
    /**
     * This is the actual implementation of {@link HexStuffAbstractions#getConfigDirectory()}.
     */
    public static Path getConfigDirectory() {
        return FMLPaths.CONFIGDIR.get();
    }
	
    public static void initPlatformSpecific() {
        HexStuffConfigForge.init();
    }
}
