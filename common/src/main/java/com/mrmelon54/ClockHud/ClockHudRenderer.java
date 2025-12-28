package com.mrmelon54.ClockHud;

import com.mrmelon54.ClockHud.enums.ClockPosition;
import com.mrmelon54.ClockHud.enums.GameTimeDisplayMode;
import com.mrmelon54.ClockHud.old.OldUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

#if MC_VER < MC_1_20_1
import com.mrmelon54.ClockHud.old.ClientGuiEvent;
import com.mrmelon54.ClockHud.old.GuiGraphics;
#else
import dev.architectury.event.events.client.ClientGuiEvent;
import net.minecraft.client.gui.GuiGraphics;
#endif

#if MC_VER >= MC_1_21
import net.minecraft.client.DeltaTracker;
#endif

public class ClockHudRenderer implements ClientGuiEvent.RenderHud {
    private ItemStack clockItemStack;

    private static long getLevelDayTime(ClientLevel level) {
        #if MC_VER < MC_1_21_11
        return level.dayTime();
        #else
        return level.getDayTime();
        #endif
    }

    private String printTime(GameTimeDisplayMode timeDisplayMode, ClientLevel level) {
        long offsetTimeInTicks = (ClockHudRenderer.getLevelDayTime(level) + 6000) % 24000;
        String minutes = String.format("%02d", (int) ((double) (offsetTimeInTicks / 10 % 100) / 100 * 60));
        long hour = offsetTimeInTicks / 1000;
        //noinspection EnhancedSwitchMigration
        switch (timeDisplayMode) {
            case TICKS:
                return String.valueOf(ClockHudRenderer.getLevelDayTime(level) % 24000);
            case HRS24:
                return hour + ":" + minutes;
            case HRS12:
                return (hour + 11) % 12 + 1 + ":" + minutes + ((hour >= 12) ? " PM" : " AM");
        }
        return "";
    }

    #if MC_VER <= MC_1_20_6
    @Override
    public void renderHud(GuiGraphics graphics, float tickDelta) {
    #else
    @Override
    public void renderHud(GuiGraphics graphics, DeltaTracker deltaTracker) {
    #endif
        ConfigStructure config = ClockHud.getConfig();
        Minecraft client = Minecraft.getInstance();
        if (OldUtils.showDebugScreen()) return;

        String clockText = client.level != null ? printTime(config.timeDisplayMode, client.level) : "";
        int textLength = client.font.width(clockText);
        int textHeight = client.font.lineHeight;

        int clockSize = config.clockIconEnabled ? 16 : 0;
        int clockGap = config.clockIconEnabled ? 5 : 0;
        int offsetForIcon = clockSize + clockGap;
        int iconOffset = config.iconPosition == ClockPosition.RIGHT ? textLength + clockGap : 0;

        int clockY = 0;
        //noinspection EnhancedSwitchMigration
        switch (config.position.getVerticalPosition()) {
            case NEUTRAL:
                clockY = (textHeight - clockSize) / 2;
                break;
            case POSITIVE:
                clockY = textHeight - clockSize;
                break;
        }

        int myX = config.xOffset;
        //noinspection EnhancedSwitchMigration
        switch (config.position.getHorizontalPosition()) {
            case NEUTRAL:
                myX = graphics.guiWidth() / 2 - (textLength + offsetForIcon) / 2 + config.xOffset;
                break;
            case POSITIVE:
                myX = graphics.guiWidth() - (textLength + offsetForIcon) + config.xOffset;
                break;
        }

        int myY = config.yOffset;
        //noinspection EnhancedSwitchMigration
        switch (config.position.getVerticalPosition()) {
            case NEUTRAL:
                myY = graphics.guiHeight() / 2 - textHeight / 2 + config.yOffset;
                break;
            case POSITIVE:
                myY = graphics.guiHeight() - textHeight + config.yOffset;
                break;
        }

        if (clockItemStack == null) clockItemStack = new ItemStack(Items.CLOCK);
        if (config.gameTimeDisplayEnabled) {
            graphics.drawString(client.font, clockText, myX + (config.iconPosition == ClockPosition.LEFT ? offsetForIcon : 0), myY, config.color);
        }
        if (config.clockIconEnabled) {
            graphics.renderItem(clockItemStack, myX + iconOffset, myY + clockY, 0);
        }
    }
}
