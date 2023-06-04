package com.spaceraceinc.logicielchevalblancdemerde.enums;


import com.spaceraceinc.logicielchevalblancdemerde.ui.StageTemplate;
import com.spaceraceinc.logicielchevalblancdemerde.views.RegisterBreakfast;
import com.spaceraceinc.logicielchevalblancdemerde.views.RegisterConsummation;
import com.spaceraceinc.logicielchevalblancdemerde.views.RegisterService;

public enum NavLink {

    FEATURE1("Liste des prestations", "Ajouter une prestation"),
    FEATURE2("Liste des consommations", "Ajouter une consommation"),
    FEATURE3("Liste des petits-déjeuners", "Ajouter un petit-déjeuner");

    private final String name;
    private final String buttonLabel;

    NavLink(final String name, final String buttonLabel) {
        this.name = name;
        this.buttonLabel = buttonLabel;
    }

    public String getName() {
        return name;
    }

    public String getButtonLabel() {
        return buttonLabel;
    }

    public static NavLink first() {
        return values()[0];
    }

    public static StageTemplate getClassFrom(String name) {
        if(name.equals(FEATURE1.getName()))
            return new RegisterService();
        else if(name.equals(FEATURE2.getName()))
            return new RegisterConsummation();
        else if(name.equals(FEATURE3.getName()))
            return new RegisterBreakfast();
        return new RegisterService();
    }
}
