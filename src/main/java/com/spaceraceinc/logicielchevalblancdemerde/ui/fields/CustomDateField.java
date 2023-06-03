package com.spaceraceinc.logicielchevalblancdemerde.ui.fields;

import com.spaceraceinc.logicielchevalblancdemerde.enums.CustomColor;
import com.spaceraceinc.logicielchevalblancdemerde.enums.CustomFont;
import javafx.geometry.Insets;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;

public class CustomDateField extends GridPane {

    private final DatePicker field = new DatePicker();

    public CustomDateField(String labelName, String tooltip) {
        final Label label = new Label(labelName + " *");

        field.setPromptText(labelName);
        field.setPrefWidth(300);
        field.setStyle(
            "-fx-border-width: 1px;-fx-border-color: " + CustomColor.BROWN.asString() + ";" +
            "-fx-background-radius: 0;" +
            "-fx-focus-color: transparent;" +
            "-fx-faint-focus-color: transparent;"
        );
        field.setEditable(false);
        field.getEditor().setPadding(new Insets(9));


        if(tooltip != null)
            field.setTooltip(new Tooltip(tooltip));

        label.setLabelFor(field);
        label.setFont(CustomFont.MONTSERRAT_REGULAR.getFont());
        label.setTextFill(CustomColor.GREY.asColor());
        label.setPadding(new Insets(0, 0, 10, 0));

        this.add(label, 1, 0);
        this.add(field, 1, 1);
    }

    public CustomDateField(String s) {
        this(s, null);
    }

    public DatePicker getField() {
        return field;
    }

}
