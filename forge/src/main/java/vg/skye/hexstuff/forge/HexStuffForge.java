package vg.skye.hexstuff.forge;

import dev.architectury.platform.forge.EventBuses;
import vg.skye.hexstuff.HexStuff;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

/**
 * This is your loading entrypoint on forge, in case you need to initialize
 * something platform-specific.
 */
@Mod(HexStuff.MOD_ID)
public class HexStuffForge {
    public HexStuffForge() {
        // Submit our event bus to let architectury register our content on the right time
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        EventBuses.registerModEventBus(HexStuff.MOD_ID, bus);
        bus.addListener(HexStuffClientForge::init);
        HexStuff.init();
    }
}
