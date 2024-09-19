package vg.skye.hexstuff.fabric;

import net.fabricmc.api.ClientModInitializer;
import vg.skye.hexstuff.HexStuffClient;

/**
 * Fabric client loading entrypoint.
 */
public class HexStuffClientFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HexStuffClient.init();
    }
}
