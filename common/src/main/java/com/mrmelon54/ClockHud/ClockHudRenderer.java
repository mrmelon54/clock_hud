package com.mrmelon54.ClockHud;

import com.mrmelon54.ClockHud.enums.ClockPosition;
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
        if (!config.clockEnabled || client.gui.getDebugOverlay().showDebugScreen()) return;

        String clockText = client.level != null ? String.valueOf(client.level.dayTime() % 24000L) : "";
        int textLength = client.font.width(clockText);
        int textHeight = client.font.lineHeight;

        int clockSize = config.iconEnabled ? 16 : 0;
        int clockGap = config.iconEnabled ? 2 : 0;
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
        if (config.iconEnabled)
            graphics.renderItem(clockItemStack, myX + iconOffset, myY + clockY, 0);
        graphics.drawString(client.font, clockText, myX + (config.iconPosition == ClockPosition.LEFT ? offsetForIcon : 0), myY, config.colour);
    }
}
