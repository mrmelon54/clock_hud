package com.mrmelon54.ClockHud.quilt;

import com.mrmelon54.ClockHud.fabriclike.ClockHudFabricLike;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;

public class ClockHudQuilt implements ModInitializer {
    @Override
    public void onInitialize(ModContainer mod) {
        ClockHudFabricLike.init();
    }
}
