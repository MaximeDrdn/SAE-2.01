package com.spaceraceinc.logicielchevalblancdemerde.views.breakfasts;

import com.spaceraceinc.logicielchevalblancdemerde.MainMenu;
import com.spaceraceinc.logicielchevalblancdemerde.modules.CustomerBreakfast;
import com.spaceraceinc.logicielchevalblancdemerde.utils.Utils;
import com.spaceraceinc.logicielchevalblancdemerde.ui.FormActions;
import com.spaceraceinc.logicielchevalblancdemerde.ui.StageTemplate;
import com.spaceraceinc.logicielchevalblancdemerde.ui.fields.CustomTextField;
import com.spaceraceinc.logicielchevalblancdemerde.ui.fields.CustomQuantityField;
import com.spaceraceinc.logicielchevalblancdemerde.ui.typography.Title;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.layout.FlowPane;

import java.util.List;
import java.util.Map;

public class RegisterBreakfast extends StageTemplate {

    private CustomQuantityField chamberNumber;
    private CustomTextField type;
    private CustomQuantityField quantity;

    public RegisterBreakfast() {
        super("Enregistrer les petits-déjeuners", 350, 350);
        this.setResizable(false);
    }

    private void showRecap(ActionEvent action) {
        int chamberNumber = this.chamberNumber.getField().getValue();
        String type = this.type.getField().getText();
        int quantity = this.quantity.getField().getValue();

        final Map<String, Boolean> invalidFields = Utils.getInvalidFieldsFrom(
                List.of("numéro de chambre", "type de prestation", "quantité de petits-déjeuners"),
                List.of(Utils.isIntFieldValid(chamberNumber), Utils.isStringFieldValid(type), Utils.isIntFieldValid(quantity))
        );
        if(invalidFields != null && invalidFields.size() > 0) {
            this.openAlert(Alert.AlertType.ERROR, "Erreur: les champs suivants ne sont pas valides: " + String.join(", ", invalidFields.keySet()));
            return;
        }

        this.close();
        this.openAlert(Alert.AlertType.INFORMATION, "Les petits-déjeuners ont été ajoutés à la chambre.");

        CustomerBreakfast customerBreakfasts = new CustomerBreakfast(chamberNumber, type, quantity);
        MainMenu.CUSTOMER_BREAKFAST_LIST.add(customerBreakfasts);
    }

    private FormActions renderActions() {
        final FormActions formActions = new FormActions("Enregistrer", "Quitter");
        formActions.setConfirmCallback(this::showRecap);
        formActions.setCancelCallback(event -> this.close());
        return formActions;
    }

    @Override
    public Node renderTopContent() {
        return new Title("Enregistrer les petits-déjeuners");
    }

    @Override
    public Node renderMainContent() {
        final FlowPane group = new FlowPane();
        this.chamberNumber = new CustomQuantityField("Numéro de la chambre", "Format: NuméroEtageXX (ex: 201)");
        this.type = new CustomTextField("Type de petit-déjeuner");
        this.quantity = new CustomQuantityField("Quantité de petits-déjeuners");

        group.setAlignment(Pos.CENTER);
        group.setVgap(10);
        group.getChildren().addAll(chamberNumber, type, quantity);
        return group;
    }

    @Override
    public Node renderBottomContent() {
        return this.renderActions();
    }

}
