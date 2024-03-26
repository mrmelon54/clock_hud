package com.mrmelon54.ClockHud.forge;

import com.mrmelon54.ClockHud.ClockHud;
import net.minecraftforge.fml.common.Mod;

@Mod(ClockHud.MOD_ID)
public class ClockHudForge {
    public ClockHudForge() {
        ClockHud.init();
    }
}
