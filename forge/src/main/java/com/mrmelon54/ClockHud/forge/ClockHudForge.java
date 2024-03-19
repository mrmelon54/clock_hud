package com.mrmelon54.ClockHud.forge;

import com.mrmelon54.ClockHud.ClockHud;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

#if MC_VER == MC_1_16_5
import me.shedaniel.architectury.platform.forge.EventBuses;
#else
import dev.architectury.platform.forge.EventBuses;
#endif

@Mod(ClockHud.MOD_ID)
public class ClockHudForge {
    public ClockHudForge() {
        // Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(ClockHud.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        ClockHud.init();
    }
}
