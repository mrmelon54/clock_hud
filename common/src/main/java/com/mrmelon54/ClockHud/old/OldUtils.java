package com.mrmelon54.ClockHud.old;

import net.minecraft.client.Minecraft;

public class OldUtils {
    public static boolean showDebugScreen() {
        #if MC_VER > MC_1_16_5
        return Minecraft.getInstance().gui.getDebugOverlay().showDebugScreen();
        #else
        return Minecraft.getInstance().options.renderDebug;
        #endif
    }
}
