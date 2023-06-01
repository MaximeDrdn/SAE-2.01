package com.example.logicielchevalblancdemerde.enums;

public enum TitleType {
    H1(17),
    H2(15),
    H3(13),
    H4(11);

    private final int fontSize;

    TitleType(final int fontSize) {
        this.fontSize = fontSize;
    }

    public int getFontSize() {
        return fontSize;
    }
}
