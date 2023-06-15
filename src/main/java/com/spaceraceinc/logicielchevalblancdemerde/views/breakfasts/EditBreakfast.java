package com.spaceraceinc.logicielchevalblancdemerde.views.breakfasts;

import com.spaceraceinc.logicielchevalblancdemerde.MainMenu;
import com.spaceraceinc.logicielchevalblancdemerde.modules.CustomerBreakfast;
import com.spaceraceinc.logicielchevalblancdemerde.ui.FormActions;
import com.spaceraceinc.logicielchevalblancdemerde.ui.StageTemplate;
import com.spaceraceinc.logicielchevalblancdemerde.ui.fields.CustomQuantityField;
import com.spaceraceinc.logicielchevalblancdemerde.ui.fields.CustomTextField;
import com.spaceraceinc.logicielchevalblancdemerde.ui.typography.Title;
import com.spaceraceinc.logicielchevalblancdemerde.utils.Utils;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.layout.FlowPane;

import java.util.List;
import java.util.Map;

public class EditBreakfast extends StageTemplate {

    private CustomTextField type;
    private CustomQuantityField quantity;

    private CustomerBreakfast data;

    public EditBreakfast(CustomerBreakfast data) {
        super("Modifier les petits-déjeuners", 350, 350);
        this.setResizable(false);

        this.data = data;
        this.updateScene();
    }

    private void showRecap(ActionEvent action) {
        String type = this.type.getField().getText();
        int quantity = this.quantity.getField().getValue();

        final Map<String, Boolean> invalidFields = Utils.getInvalidFieldsFrom(
                List.of("type de prestation", "quantité de petits-déjeuners"),
                List.of(Utils.isStringFieldValid(type), Utils.isIntFieldValid(quantity))
        );
        if(invalidFields != null && invalidFields.size() > 0) {
            this.openAlert(Alert.AlertType.ERROR, "Erreur: les champs suivants ne sont pas valides: " + String.join(", ", invalidFields.keySet()));
            return;
        }

        this.close();
        this.openAlert(Alert.AlertType.INFORMATION, "Les petits-déjeuners de la chambre ont été modifiés.");

        CustomerBreakfast customerBreakfasts = new CustomerBreakfast(this.data.getChamberNumber(), type, quantity);
        MainMenu.CUSTOMER_BREAKFAST_LIST.set(MainMenu.CUSTOMER_BREAKFAST_LIST.indexOf(this.data), customerBreakfasts);
    }

    private FormActions renderActions() {
        final FormActions formActions = new FormActions("Modifier", "Quitter");
        formActions.setConfirmCallback(this::showRecap);
        formActions.setCancelCallback(event -> this.close());
        return formActions;
    }

    @Override
    public Node renderTopContent() {
        return new Title("Modifier les petits-déjeuners");
    }

    @Override
    public Node renderMainContent() {
        if(this.data == null)
            return null;
        final FlowPane group = new FlowPane();

        this.type = new CustomTextField("Type de petit-déjeuner");
        this.type.setValue(this.data.getType());

        this.quantity = new CustomQuantityField("Quantité de petits-déjeuners");
        this.quantity.setValue(this.data.getQuantity());

        group.setAlignment(Pos.CENTER);
        group.setVgap(10);
        group.getChildren().addAll(type, quantity);
        return group;
    }

    @Override
    public Node renderBottomContent() {
        return this.renderActions();
    }

}
