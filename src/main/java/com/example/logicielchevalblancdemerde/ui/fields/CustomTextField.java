package com.example.logicielchevalblancdemerde.ui.fields;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;

public class CustomTextField extends GridPane {

    private final TextField field = new TextField();

    public CustomTextField(String labelName, String tooltip) {
        final Label label = new Label(labelName + " *");

        field.setPromptText(labelName);
        field.setPrefWidth(300);
        if(tooltip != null)
            field.setTooltip(new Tooltip(tooltip));
        label.setLabelFor(field);

        this.add(label, 1, 0);
        this.add(field, 1, 1);
    }

    public TextField getField() {
        return field;
    }

    public CustomTextField(String labelName) {
        this(labelName, null);
    }

}
