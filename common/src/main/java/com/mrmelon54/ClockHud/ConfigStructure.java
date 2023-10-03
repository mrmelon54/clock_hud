package com.mrmelon54.ClockHud;

import com.mrmelon54.ClockHud.enums.ClockPosition;
import com.mrmelon54.ClockHud.enums.DisplayPosition;
import com.mrmelon54.ClockHud.enums.GameTimeDisplayMode;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;

@Config(name = "clock-hud")
@Config.Gui.Background("minecraft:textures/block/gold_block.png")
public class ConfigStructure implements ConfigData {
    public boolean gameTimeDisplayEnabled = true;

    @ConfigEntry.Gui.EnumHandler(option = ConfigEntry.Gui.EnumHandler.EnumDisplayOption.BUTTON)
    public GameTimeDisplayMode timeDisplayMode = GameTimeDisplayMode.HRS12;

    public boolean clockIconEnabled = true;

    @ConfigEntry.Gui.EnumHandler(option = ConfigEntry.Gui.EnumHandler.EnumDisplayOption.BUTTON)
    public ClockPosition iconPosition = ClockPosition.RIGHT;

    @ConfigEntry.Gui.EnumHandler(option = ConfigEntry.Gui.EnumHandler.EnumDisplayOption.BUTTON)
    public DisplayPosition position = DisplayPosition.BOTTOM_RIGHT;


    @ConfigEntry.ColorPicker
    public int color = 0xffffff;

    public int xOffset = -5;

    public int yOffset = -5;
}
