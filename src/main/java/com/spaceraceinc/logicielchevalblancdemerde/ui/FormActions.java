package com.spaceraceinc.logicielchevalblancdemerde.ui;

import com.spaceraceinc.logicielchevalblancdemerde.ui.fields.CustomButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

public class FormActions extends HBox {

    private final CustomButton confirmForm;
    private final CustomButton cancelForm;

    public FormActions(String sConfirmForm, String sCancelForm) {
        this.confirmForm = new CustomButton(sConfirmForm);
        this.cancelForm = new CustomButton(sCancelForm);

        this.confirmForm.setDefaultButton(true);

        this.setAlignment(Pos.BOTTOM_RIGHT);
        this.setSpacing(10);
        this.getChildren().addAll(this.cancelForm, this.confirmForm);
    }

    public void setConfirmCallback(EventHandler<ActionEvent> event) {
        this.confirmForm.setOnAction(event);
    }

    public void setCancelCallback(EventHandler<ActionEvent> event) {
        this.cancelForm.setOnAction(event);
    }
}
