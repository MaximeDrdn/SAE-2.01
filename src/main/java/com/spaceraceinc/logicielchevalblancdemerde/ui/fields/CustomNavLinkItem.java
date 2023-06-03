package com.spaceraceinc.logicielchevalblancdemerde.ui.fields;

import com.spaceraceinc.logicielchevalblancdemerde.enums.CustomColor;
import com.spaceraceinc.logicielchevalblancdemerde.enums.CustomFont;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class CustomNavLinkItem extends Button {

    private final boolean active;

    public CustomNavLinkItem(String s, boolean active) {
        this.active = active;
        this.setText(s);
        this.setCursor(Cursor.HAND);

        this.setFont(CustomFont.GARAMOND_REGULAR.getFont());
        this.setTextFill(this.getColor());
        this.setStyle("-fx-background-color: transparent");

        this.setOnMouseEntered(event -> this.setTextFill(CustomColor.BROWN.asColor()));
        this.setOnMouseExited(event -> this.setTextFill(this.getColor()));
    }

    private Color getColor() {
        return this.active ? CustomColor.BROWN.asColor() : CustomColor.GREY.asColor();
    }

    public CustomNavLinkItem(String s) {
        this(s, false);
    }

}
