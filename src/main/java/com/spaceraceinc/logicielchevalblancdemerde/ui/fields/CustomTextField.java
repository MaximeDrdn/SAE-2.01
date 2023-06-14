package com.spaceraceinc.logicielchevalblancdemerde.ui.fields;

import com.spaceraceinc.logicielchevalblancdemerde.enums.CustomColor;
import com.spaceraceinc.logicielchevalblancdemerde.enums.CustomFont;
import javafx.geometry.Insets;
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
        field.setStyle(
                "-fx-border-width: 1px;" +
                "-fx-border-color: " + CustomColor.BROWN.asString() + ";" +
                "-fx-border-radius: 0;" +
                "-fx-focus-color: transparent;" +
                "-fx-faint-focus-color: transparent;"
        );
        field.setFont(CustomFont.MONTSERRAT_REGULAR.getFont());
        field.setPadding(new Insets(10));

        if(tooltip != null)
            field.setTooltip(new Tooltip(tooltip));

        label.setLabelFor(field);
        label.setFont(CustomFont.MONTSERRAT_REGULAR.getFont());
        label.setTextFill(CustomColor.GREY.asColor());
        label.setPadding(new Insets(0, 0, 10, 0));

        this.add(label, 1, 0);
        this.add(field, 1, 1);
    }

    public TextField getField() {
        return field;
    }

    public CustomTextField(String labelName) {
        this(labelName, null);
    }

    public void setValue(String value) {
        this.getField().setText(value);
    }

}
