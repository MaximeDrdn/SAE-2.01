package com.spaceraceinc.logicielchevalblancdemerde.enums;


import com.spaceraceinc.logicielchevalblancdemerde.modules.CustomerBreakfast;
import com.spaceraceinc.logicielchevalblancdemerde.modules.CustomerConsummation;
import com.spaceraceinc.logicielchevalblancdemerde.modules.CustomerPrestation;
import com.spaceraceinc.logicielchevalblancdemerde.ui.SearchResultField;
import com.spaceraceinc.logicielchevalblancdemerde.ui.StageTemplate;
import com.spaceraceinc.logicielchevalblancdemerde.views.breakfasts.BreakfastDetails;
import com.spaceraceinc.logicielchevalblancdemerde.views.breakfasts.EditBreakfast;
import com.spaceraceinc.logicielchevalblancdemerde.views.breakfasts.RegisterBreakfast;
import com.spaceraceinc.logicielchevalblancdemerde.views.consummations.ConsummationDetails;
import com.spaceraceinc.logicielchevalblancdemerde.views.consummations.EditConsummation;
import com.spaceraceinc.logicielchevalblancdemerde.views.consummations.RegisterConsummation;
import com.spaceraceinc.logicielchevalblancdemerde.views.prestations.EditPrestation;
import com.spaceraceinc.logicielchevalblancdemerde.views.prestations.PrestationDetails;
import com.spaceraceinc.logicielchevalblancdemerde.views.prestations.RegisterPrestation;

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

    public StageTemplate getRegisterStage() {
        String name = this.getName();
        if(name.equals(FEATURE1.getName()))
            return new RegisterPrestation();
        else if(name.equals(FEATURE2.getName()))
            return new RegisterConsummation();
        else if(name.equals(FEATURE3.getName()))
            return new RegisterBreakfast();
        return new RegisterPrestation();
    }

    public StageTemplate getDetailsStage(Object data) {
        String name = this.getName();
        if(name.equals(FEATURE2.getName()))
            return new ConsummationDetails((CustomerConsummation) data);
        else if(name.equals(FEATURE3.getName()))
            return new BreakfastDetails((CustomerBreakfast) data);
        return new PrestationDetails((CustomerPrestation) data);
    }

    public StageTemplate getModificationStage(Object data) {
        String name = this.getName();
        if(name.equals(FEATURE2.getName()))
            return new EditConsummation((CustomerConsummation) data);
        else if(name.equals(FEATURE3.getName()))
            return new EditBreakfast((CustomerBreakfast) data);
        return new EditPrestation((CustomerPrestation) data);
    }

    public SearchResultField.SearchFieldData getSearchFieldData(Object data) {
        String name = this.getName();
        if(name.equals(FEATURE2.getName())) {
            CustomerConsummation customerConsummation = (CustomerConsummation) data;
            return new SearchResultField.SearchFieldData(customerConsummation.getChamberNumber(), customerConsummation.getRegistrationDate());
        } else if(name.equals(FEATURE3.getName())) {
            CustomerBreakfast customerBreakfast = (CustomerBreakfast) data;
            return new SearchResultField.SearchFieldData(customerBreakfast.getChamberNumber(), customerBreakfast.getRegistrationDate());
        }
        CustomerPrestation customerPrestation = (CustomerPrestation) data;
        return new SearchResultField.SearchFieldData(customerPrestation.getChamberNumber(), customerPrestation.getRegistrationDate());
    }
}
