package com.mrmelon54.ClockHud.neoforge;

import com.mrmelon54.ClockHud.ClockHud;
import net.neoforged.fml.common.Mod;

@Mod(ClockHud.MOD_ID)
public class ClockHudNeoForge {
    public ClockHudNeoForge() {
        ClockHud.init();
    }
}
