package com.mrmelon54.ClockHud;

import com.mrmelon54.ClockHud.enums.ClockPosition;
import com.mrmelon54.ClockHud.enums.GameTimeDisplayMode;
import dev.architectury.event.events.client.ClientGuiEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class ClockHudRenderer implements ClientGuiEvent.RenderHud {
    private ItemStack clockItemStack;

    @Override
    public void renderHud(GuiGraphics graphics, float tickDelta) {
        ConfigStructure config = ClockHud.getConfig();
        Minecraft client = Minecraft.getInstance();
        if (client.options.renderDebug) return;
        
        long offsettedTimeInTicks = (client.level.dayTime() + 6000) % 24000;
        String minutes = String.format("%02d", (int) ((double)(offsettedTimeInTicks/10 % 100)/100*60));

        String clockText = client.level != null ? switch (config.timeDisplayMode) {
            case TICKS -> String.valueOf(client.level.dayTime() % 24000);
            case HRS24 -> String.valueOf(offsettedTimeInTicks/1000) + ":" + minutes;
            case HRS12 -> String.valueOf(((offsettedTimeInTicks/1000) + 11) % 12 + 1) + ":" + minutes + ((offsettedTimeInTicks/1000 >= 12) ? " PM" : " AM");
            default -> "";
        } : "";
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
            default -> 0;
        };

        int myX = switch (config.position.getHorizontalPosition()) {
            case NEGATIVE -> config.xOffset;
            case NEUTRAL -> graphics.guiWidth() / 2 - (textLength + offsetForIcon) / 2 + config.xOffset;
            case POSITIVE -> graphics.guiWidth() - (textLength + offsetForIcon) + config.xOffset;
            default -> config.xOffset;
        };
        int myY = switch (config.position.getVerticalPosition()) {
            case NEGATIVE -> config.yOffset;
            case NEUTRAL -> graphics.guiHeight() / 2 - textHeight / 2 + config.yOffset;
            case POSITIVE -> graphics.guiHeight() - textHeight + config.yOffset;
            default -> config.yOffset;
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
