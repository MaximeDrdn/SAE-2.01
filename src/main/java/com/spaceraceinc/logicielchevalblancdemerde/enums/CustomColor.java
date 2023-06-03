package com.spaceraceinc.logicielchevalblancdemerde.enums;


import javafx.scene.paint.Color;

public enum CustomColor {

    BROWN("#A08C6A"),
    GREY("#9F9794"),
    BLACK("#141313"),
    WHITE("white");

    private final String colorHex;

    CustomColor(final String colorHex) {
        this.colorHex = colorHex;
    }

    public String asString() {
        return colorHex;
    }

    public Color asColor() {
        return Color.web(colorHex);
    }
}
