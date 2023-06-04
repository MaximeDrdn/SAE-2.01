package com.spaceraceinc.logicielchevalblancdemerde.ui.fields;

import com.spaceraceinc.logicielchevalblancdemerde.enums.CustomColor;
import com.spaceraceinc.logicielchevalblancdemerde.enums.CustomFont;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class CustomQuantityField extends GridPane {

    public static final int MIN_VALUE = 0;
    public static final int DEFAULT_VALUE = 0;

    private final Spinner<Integer> field = new Spinner<>();

    public CustomQuantityField(String labelName, String tooltip) {
        final Label label = new Label(labelName + " *");

        field.setPromptText(labelName);
        field.setPrefWidth(300);
        field.setStyle("-fx-border-width: 1px;-fx-border-color: " + CustomColor.BROWN.asString() + ";-fx-background-radius: 0;-fx-focus-color: transparent;-fx-faint-focus-color: transparent;");
        field.getEditor().setFont(CustomFont.MONTSERRAT_REGULAR.getFont());
        field.getEditor().setPadding(new Insets(9));

        if(tooltip != null)
            field.setTooltip(new Tooltip(tooltip));
        label.setLabelFor(field);
        label.setFont(CustomFont.MONTSERRAT_REGULAR.getFont());
        label.setTextFill(CustomColor.GREY.asColor());
        label.setPadding(new Insets(0, 0, 10, 0));


        field.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(MIN_VALUE, Integer.MAX_VALUE, DEFAULT_VALUE));
        field.setEditable(true);

        final TextField editor = field.getEditor();
        editor.textProperty().addListener(((observableValue, oldValue, newValue) -> {
            String value;
            try {
                if(newValue.trim().equals(""))
                    value = Integer.toString(MIN_VALUE);
                else {
                    int intValue = Integer.parseInt(newValue);
                    value = Integer.toString(intValue);
                }
            } catch(NumberFormatException error) {
                value = oldValue;
            }
            editor.setText(value);
        }));

        this.add(label, 1, 0);
        this.add(field, 1, 1);
    }

    public Spinner<Integer> getField() {
        return field;
    }

    public CustomQuantityField(String labelName) {
        this(labelName, null);
    }

}
