package com.mrmelon54.ClockHud.forge;

import com.mrmelon54.ClockHud.ClockHud;
import me.shedaniel.architectury.platform.forge.EventBuses;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

#if MC_VER > MC_1_16_5
import net.minecraftforge.client.ConfigScreenHandler;
import net.minecraftforge.client.ConfigScreenHandler.ConfigScreenFactory;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import dev.architectury.forge.ArchitecturyForge;
import dev.architectury.platform.forge.EventBuses;
#else
import me.shedaniel.architectury.forge.ArchitecturyForge;
import me.shedaniel.architectury.platform.forge.EventBuses;
#endif

@Mod(ClockHud.MOD_ID)
public class ClockHudForge {
    public ClockHudForge() {
        // Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(ClockHud.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        #if MC_VER > MC_1_16_5
        ModLoadingContext.get().registerExtensionPoint(ConfigScreenFactory.class, () -> new ConfigScreenFactory((mc, screen) -> ClockHud.createConfigScreen(screen).get()));
        #else
        ModLoadingContext.get().registerExtensionPoint(ExtensionPoint.CONFIGGUIFACTORY, () -> (minecraft, screen) -> ClockHud.createConfigScreen(screen).get());
        #endif

        ClockHud.init();
    }
}
