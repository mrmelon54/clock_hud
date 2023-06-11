package com.mrmelon54.ClockHud.enums;

public enum ClockPosition {
    LEFT("Left"),
    RIGHT("Right");

    private final String name;

    ClockPosition(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
