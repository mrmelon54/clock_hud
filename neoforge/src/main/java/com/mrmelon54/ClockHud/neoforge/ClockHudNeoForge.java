package com.mrmelon54.ClockHud.neoforge;

import com.mrmelon54.ClockHud.ClockHud;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
#if MC_VER <= MC_1_20_4
import net.neoforged.neoforge.client.ConfigScreenHandler.ConfigScreenFactory;
#else
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
#endif

@Mod(ClockHud.MOD_ID)
public class ClockHudNeoForge {
    public ClockHudNeoForge() {
        // Submit our event bus to let architectury register our content on the right time
        #if MC_VER <= MC_1_20_4
        ModLoadingContext.get().registerExtensionPoint(ConfigScreenFactory.class, () -> new ConfigScreenFactory((mc, screen) -> ClockHud.createConfigScreen(screen).get()));
        #else
        ModLoadingContext.get().registerExtensionPoint(IConfigScreenFactory.class, () -> (mc, screen) -> ClockHud.createConfigScreen(screen).get());
        #endif

        ClockHud.init();
    }
}
