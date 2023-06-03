package com.spaceraceinc.logicielchevalblancdemerde.enums;

public enum NavLink {

    FEATURE1("Liste des prestations"),
    FEATURE2("Liste des consommations"),
    FEATURE3("Liste des petits-déjeuners");

    private final String name;

    NavLink(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static NavLink first() {
        return values()[0];
    }
}
