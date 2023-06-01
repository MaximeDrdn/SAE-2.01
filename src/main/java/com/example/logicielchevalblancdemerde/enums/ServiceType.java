package com.example.logicielchevalblancdemerde.enums;

public enum ServiceType {

    NAVETTE("Navette"),
    PRESSING("Pressing");

    private final String name;

    ServiceType(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
