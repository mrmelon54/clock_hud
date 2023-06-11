package com.mrmelon54.ClockHud.enums;

public enum DisplayPosition {
    TOP_LEFT("Top Left", AxisPosition.NEGATIVE, AxisPosition.NEGATIVE),
    TOP_MIDDLE("Top Middle", AxisPosition.NEGATIVE, AxisPosition.NEUTRAL),
    TOP_RIGHT("Top Right", AxisPosition.NEGATIVE, AxisPosition.POSITIVE),
    MIDDLE_RIGHT("Middle Right", AxisPosition.NEUTRAL, AxisPosition.POSITIVE),
    BOTTOM_RIGHT("Bottom Right", AxisPosition.POSITIVE, AxisPosition.POSITIVE),
    BOTTOM_MIDDLE("Bottom Middle", AxisPosition.POSITIVE, AxisPosition.NEUTRAL),
    BOTTOM_LEFT("Bottom Left", AxisPosition.POSITIVE, AxisPosition.NEGATIVE),
    MIDDLE_LEFT("Middle Left", AxisPosition.NEUTRAL, AxisPosition.NEGATIVE);

    private final String name;
    private final AxisPosition vertical;
    private final AxisPosition horizontal;

    DisplayPosition(String name, AxisPosition vertical, AxisPosition horizontal) {
        this.name = name;
        this.vertical = vertical;
        this.horizontal = horizontal;
    }

    public String toString() {
        return name;
    }

    public AxisPosition getVerticalPosition() {
        return vertical;
    }

    public AxisPosition getHorizontalPosition() {
        return horizontal;
    }
}
