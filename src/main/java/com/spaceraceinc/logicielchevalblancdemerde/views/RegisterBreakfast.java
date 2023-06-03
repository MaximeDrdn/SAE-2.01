package com.spaceraceinc.logicielchevalblancdemerde.views;

import com.spaceraceinc.logicielchevalblancdemerde.Utils;
import com.spaceraceinc.logicielchevalblancdemerde.ui.FormActions;
import com.spaceraceinc.logicielchevalblancdemerde.ui.StageTemplate;
import com.spaceraceinc.logicielchevalblancdemerde.ui.fields.CustomButton;
import com.spaceraceinc.logicielchevalblancdemerde.ui.fields.CustomTextField;
import com.spaceraceinc.logicielchevalblancdemerde.ui.fields.CustomQuantityField;
import com.spaceraceinc.logicielchevalblancdemerde.ui.Title;
import com.spaceraceinc.logicielchevalblancdemerde.enums.TitleType;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class RegisterBreakfast extends StageTemplate {

    private CustomQuantityField chamberNumber;
    private CustomTextField type;
    private CustomQuantityField quantity;

    private Title title;
    private FlowPane mainContent;
    private HBox bottomContent;

    public RegisterBreakfast() {
        super("Enregistrer les petits-déjeuners");
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

        this.title.setText("Récapitulatif des petits-déjeuners");

        CustomButton exit = new CustomButton("Quitter");
        exit.setOnAction(event -> this.close());

        this.updatePaneComponent(this.mainContent, this.renderRecap());
        this.updatePaneComponent(this.bottomContent, exit);
    }

    private FlowPane renderRecap() {
        final FlowPane group = new FlowPane();
        group.getChildren().addAll(
            new Title("Date d'enregistrement: " + Utils.formatDate(LocalDateTime.now()), TitleType.H2),
            new Title("Numéro de la chambre: " + chamberNumber.getField().getValue(), TitleType.H2),
            new Title("Type de petit déjeuner: " + type.getField().getText(), TitleType.H2),
            new Title("Quantité de petits-déjeuners: " + quantity.getField().getValue(), TitleType.H2)
        );
        return group;
    }

    private FormActions renderActions() {
        final FormActions formActions = new FormActions("Enregistrer", "Quitter");
        formActions.setConfirmCallback(this::showRecap);
        formActions.setCancelCallback(event -> this.close());
        return formActions;
    }

    private FlowPane renderFields() {
        final FlowPane group = new FlowPane();
        this.chamberNumber = new CustomQuantityField("Numéro de la chambre");
        this.type = new CustomTextField("Type de petit-déjeuner");
        this.quantity = new CustomQuantityField("Quantité de petits-déjeuners");

        group.getChildren().addAll(chamberNumber, type, quantity);
        return group;
    }

    @Override
    public Node renderTopContent() {
        if(this.title == null)
            this.title = new Title("Enregistrer les petits-déjeuners");
        return this.title;
    }

    @Override
    public Node renderMainContent() {
        if(this.mainContent == null)
            this.mainContent = this.renderFields();
        return this.mainContent;
    }

    @Override
    public Node renderBottomContent() {
        if(this.bottomContent == null)
            this.bottomContent = this.renderActions();
        return this.bottomContent;
    }

}
