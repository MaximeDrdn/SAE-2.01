package com.spaceraceinc.logicielchevalblancdemerde.enums;

import javafx.scene.text.Font;

public enum CustomFont {

    GARAMOND_BOLD("garamond-bold.ttf", 16),
    GARAMOND_REGULAR("garamond-regular.ttf", 16),
    MONTSERRAT_REGULAR("montserrat-regular.ttf", 12);

    private final Font font;

    CustomFont(final String fontName, final int size) {
        this.font = Font.loadFont("file:resources/fonts/"+fontName, size);
    }

    public Font getFont() {
        return font;
    }
}
