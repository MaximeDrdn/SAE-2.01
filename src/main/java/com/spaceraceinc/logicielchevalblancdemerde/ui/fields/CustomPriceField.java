package com.spaceraceinc.logicielchevalblancdemerde.ui.fields;

import com.spaceraceinc.logicielchevalblancdemerde.enums.CustomColor;
import com.spaceraceinc.logicielchevalblancdemerde.enums.CustomFont;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

public class CustomPriceField extends GridPane {

    public static final double MIN_VALUE = 0;
    public static final double DEFAULT_VALUE = 0;
    public static final double STEP_BY = .1;

    private final Spinner<Double> field = new Spinner<>();

    public CustomPriceField(String labelName, String tooltip) {
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
        field.getEditor().setFont(CustomFont.MONTSERRAT_REGULAR.getFont());
        field.getEditor().setPadding(new Insets(9));

        if(tooltip != null)
            field.setTooltip(new Tooltip(tooltip));
        label.setLabelFor(field);
        label.setFont(CustomFont.MONTSERRAT_REGULAR.getFont());
        label.setTextFill(CustomColor.GREY.asColor());
        label.setPadding(new Insets(0, 0, 10, 0));


        field.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(MIN_VALUE, Double.MAX_VALUE, DEFAULT_VALUE, STEP_BY));
        field.setEditable(true);

        final TextField editor = field.getEditor();
        editor.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.matches("^([0-9]*([.|,]))?([0-9]?)+$"))
                editor.setText(oldValue);
        });
        editor.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue && editor.textProperty().getValue().trim().equals(""))
                editor.setText("0");
        });

        this.add(label, 1, 0);
        this.add(field, 1, 1);
    }

    public Spinner<Double> getField() {
        return field;
    }

    public CustomPriceField(String labelName) {
        this(labelName, null);
    }

    public void setValue(double value) {
        this.getField().getValueFactory().setValue(value);
    }

}
