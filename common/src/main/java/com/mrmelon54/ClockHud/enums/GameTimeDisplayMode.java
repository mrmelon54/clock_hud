package com.mrmelon54.ClockHud.enums;

public enum GameTimeDisplayMode {
    TICKS("Ticks"),
    HRS24("24 Hour"),
    HRS12("12 Hour");

    private final String name;

    GameTimeDisplayMode(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
