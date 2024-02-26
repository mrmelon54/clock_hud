package com.mrmelon54.ClockHud;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.minecraft.client.gui.screens.Screen;

#if MC_VER > MC_1_16_5
    import dev.architectury.event.events.client.ClientGuiEvent;
#else
import com.mrmelon54.ClockHud.old.ClientGuiEvent;
#endif

import java.util.function.Supplier;

public class ClockHud {
    public static final String MOD_ID = "clock_hud";
    private static ConfigStructure config;

    public static ConfigStructure getConfig() {
        return config;
    }

    public static void init() {
        AutoConfig.register(ConfigStructure.class, JanksonConfigSerializer::new);
        config = AutoConfig.getConfigHolder(ConfigStructure.class).getConfig();

        ClientGuiEvent.RENDER_HUD.register(new ClockHudRenderer());
    }

    public static Supplier<Screen> createConfigScreen(Screen screen) {
        return AutoConfig.getConfigScreen(ConfigStructure.class, screen);
    }
}
