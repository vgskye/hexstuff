package vg.skye.hexstuff.forge;

import vg.skye.hexstuff.HexStuffClient;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

/**
 * Forge client loading entrypoint.
 */
public class HexStuffClientForge {
    public static void init(FMLClientSetupEvent event) {
        HexStuffClient.init();
    }
}
