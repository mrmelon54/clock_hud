package com.mrmelon54.ClockHud.neoforge;

import com.mrmelon54.ClockHud.ClockHud;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.javafmlmod.FMLJavaModLoadingContext;
import net.neoforged.neoforge.client.ConfigScreenHandler.ConfigScreenFactory;

@Mod(ClockHud.MOD_ID)
public class ClockHudNeoForge {
    public ClockHudNeoForge() {
        // Submit our event bus to let architectury register our content on the right time
        ModLoadingContext.get().registerExtensionPoint(ConfigScreenFactory.class, () -> new ConfigScreenFactory((mc, screen) -> ClockHud.createConfigScreen(screen).get()));
        ClockHud.init();
    }
}
