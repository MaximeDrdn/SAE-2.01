package com.spaceraceinc.logicielchevalblancdemerde.ui.fields;

import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.util.ArrayList;

public class CustomRadioList extends ToggleGroup {

    private final ArrayList<RadioButton> buttons;

    public CustomRadioList() {
        buttons = new ArrayList<>();
    }

    public void addButton(String s, Boolean isDefault) {
        RadioButton button = new RadioButton(s);
        button.setToggleGroup(this);
        if(isDefault)
            button.setSelected(true);
        this.buttons.add(button);
    }

    public void addButton(String s) {
        this.addButton(s, false);
    }

    public ArrayList<RadioButton> getButtons() {
        return buttons;
    }
}
