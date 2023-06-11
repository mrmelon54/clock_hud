package com.mrmelon54.ClockHud.fabric;

import com.mrmelon54.ClockHud.fabriclike.ClockHudFabricLike;
import net.fabricmc.api.ModInitializer;

public class ClockHudFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        ClockHudFabricLike.init();
    }
}
