package com.mrmelon54.ClockHud;

import com.mrmelon54.ClockHud.enums.ClockPosition;
import com.mrmelon54.ClockHud.enums.GameTimeDisplayMode;
import dev.architectury.event.events.client.ClientGuiEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class ClockHudRenderer implements ClientGuiEvent.RenderHud {
    private ItemStack clockItemStack;

    private String printTime(GameTimeDisplayMode timeDisplayMode, ClientLevel level) {
        long offsettedTimeInTicks = (level.dayTime() + 6000) % 24000;
        String minutes = String.format("%02d", (int) ((double) (offsettedTimeInTicks / 10 % 100) / 100 * 60));
        long hour = offsettedTimeInTicks / 1000;
        return switch (timeDisplayMode) {
            case TICKS -> String.valueOf(level.dayTime() % 24000);
            case HRS24 -> hour + ":" + minutes;
            case HRS12 -> (hour + 11) % 12 + 1 + ":" + minutes + ((hour >= 12) ? " PM" : " AM");
        };
    }

    @Override
    public void renderHud(GuiGraphics graphics, float tickDelta) {
        ConfigStructure config = ClockHud.getConfig();
        Minecraft client = Minecraft.getInstance();
        if (client.gui.getDebugOverlay().showDebugScreen()) return;

        String clockText = client.level != null ? printTime(config.timeDisplayMode, client.level) : "";
        int textLength = client.font.width(clockText);
        int textHeight = client.font.lineHeight;

        int clockSize = config.clockIconEnabled ? 16 : 0;
        int clockGap = config.clockIconEnabled ? 5 : 0;
        int offsetForIcon = clockSize + clockGap;
        int iconOffset = config.iconPosition == ClockPosition.RIGHT ? textLength + clockGap : 0;
        int clockY = switch (config.position.getVerticalPosition()) {
            case NEGATIVE -> 0;
            case NEUTRAL -> (textHeight - clockSize) / 2;
            case POSITIVE -> textHeight - clockSize;
        };

        int myX = switch (config.position.getHorizontalPosition()) {
            case NEGATIVE -> config.xOffset;
            case NEUTRAL -> graphics.guiWidth() / 2 - (textLength + offsetForIcon) / 2 + config.xOffset;
            case POSITIVE -> graphics.guiWidth() - (textLength + offsetForIcon) + config.xOffset;
        };
        int myY = switch (config.position.getVerticalPosition()) {
            case NEGATIVE -> config.yOffset;
            case NEUTRAL -> graphics.guiHeight() / 2 - textHeight / 2 + config.yOffset;
            case POSITIVE -> graphics.guiHeight() - textHeight + config.yOffset;
        };

        if (clockItemStack == null) clockItemStack = new ItemStack(Items.CLOCK);
        if (config.gameTimeDisplayEnabled) {
            graphics.drawString(client.font, clockText, myX + (config.iconPosition == ClockPosition.LEFT ? offsetForIcon : 0), myY, config.color);
        }
        if (config.clockIconEnabled) {
            graphics.renderItem(clockItemStack, myX + iconOffset, myY + clockY, 0);
        }
    }
}
