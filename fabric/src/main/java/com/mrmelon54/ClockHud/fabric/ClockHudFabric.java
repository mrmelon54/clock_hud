package com.mrmelon54.ClockHud.fabric;

import com.mrmelon54.ClockHud.ClockHud;
import net.fabricmc.api.ModInitializer;

public class ClockHudFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        ClockHud.init();
    }
}
