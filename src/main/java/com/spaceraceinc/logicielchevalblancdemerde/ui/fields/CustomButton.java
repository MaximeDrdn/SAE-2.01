package com.spaceraceinc.logicielchevalblancdemerde.ui.fields;

import com.spaceraceinc.logicielchevalblancdemerde.enums.CustomColor;
import com.spaceraceinc.logicielchevalblancdemerde.enums.CustomFont;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Button;

public class CustomButton extends Button {

    public CustomButton(String s) {
        super(s);

        this.defaultSettings();
        this.setOnMouseEntered(event -> this.setHoverStyle());
        this.setOnMouseExited(event -> this.setDefaultStyle());
    }

    public CustomButton(CustomImage image) {
        super();

        this.setGraphic(image);
        this.defaultSettings();
        this.setMinWidth(image.getFitWidth());
        this.setMinHeight(image.getFitHeight());
    }

    private void defaultSettings() {
        this.setMinWidth(150);
        this.setPadding(new Insets(10));
        this.setCursor(Cursor.HAND);
        this.setFont(CustomFont.MONTSERRAT_REGULAR.getFont());

        this.setDefaultStyle();
    }

    private void setHoverStyle() {
        this.setStyle("-fx-background-radius: 0;-fx-background-color: transparent;-fx-border-width: 1px;-fx-border-color:"+CustomColor.BROWN.asString());
        this.setTextFill(CustomColor.BROWN.asColor());
    }

    private void setDefaultStyle() {
        this.setStyle("-fx-background-color: "+CustomColor.BROWN.asString()+";-fx-background-radius: 0;-fx-border-width: 1px;-fx-border-color:"+CustomColor.BROWN.asString());
        this.setTextFill(CustomColor.WHITE.asColor());
    }

}
