package com.mrmelon54.ClockHud.forge;

import dev.architectury.forge.ArchitecturyForge;
import dev.architectury.platform.forge.EventBuses;
import com.mrmelon54.ClockHud.ClockHud;
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.client.ConfigScreenHandler.ConfigScreenFactory;
import net.minecraftforge.common.ForgeConfig;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ClockHud.MOD_ID)
public class ClockHudForge {
    public ClockHudForge() {
        // Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(ClockHud.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        ModLoadingContext.get().registerExtensionPoint(ConfigScreenFactory.class, () -> new ConfigScreenFactory((mc, screen) -> ClockHud.createConfigScreen(screen).get()));

        ClockHud.init();
    }
}
