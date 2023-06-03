package com.spaceraceinc.logicielchevalblancdemerde.modals;

import com.spaceraceinc.logicielchevalblancdemerde.Utils;
import com.spaceraceinc.logicielchevalblancdemerde.modules.Consummation;
import com.spaceraceinc.logicielchevalblancdemerde.ui.StageTemplate;
import com.spaceraceinc.logicielchevalblancdemerde.ui.fields.CustomTextField;
import com.spaceraceinc.logicielchevalblancdemerde.ui.fields.CustomQuantityField;
import com.spaceraceinc.logicielchevalblancdemerde.ui.FormActions;
import com.spaceraceinc.logicielchevalblancdemerde.ui.typography.Title;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.layout.FlowPane;

import java.util.List;
import java.util.Map;

public class AddConsummationModal extends StageTemplate {

    private final ObservableList<Consummation> consummations;

    private CustomTextField type;
    private CustomTextField label;
    private CustomQuantityField quantity;

    public AddConsummationModal(ObservableList<Consummation> consummations) {
        super("Ajouter une consomamtion", 400, 300);

        this.consummations = consummations;
    }

    private void addConsummation(String type, String label, int quantity) {
        this.consummations.add(new Consummation(type, label, quantity));
    }

    private void sendForm(ActionEvent action) {
        final String typeValue = type.getField().getText();
        final String labelValue = label.getField().getText();
        final int quantityValue = quantity.getField().getValue();

        final Map<String, Boolean> invalidFields = Utils.getInvalidFieldsFrom(
                List.of("type de prestation", "libellé", "quantité"),
                List.of(Utils.isStringFieldValid(typeValue), Utils.isStringFieldValid(labelValue), Utils.isIntFieldValid(quantityValue))
        );
        if(invalidFields != null && invalidFields.size() > 0) {
            this.openAlert(Alert.AlertType.ERROR, "Erreur: les champs suivants ne sont pas valides: " + String.join(", ", invalidFields.keySet()));
            return;
        }
        this.addConsummation(typeValue, labelValue, quantityValue);
        this.openAlert(Alert.AlertType.INFORMATION, "Consommation ajoutée")
                .setOnCloseRequest(event -> this.close());
    }

    @Override
    public Node renderTopContent() {
        return new Title("Ajouter une consommation");
    }

    @Override
    public FlowPane renderMainContent() {
        this.type = new CustomTextField("Type de prestation");
        this.label = new CustomTextField("Libellé");
        this.quantity = new CustomQuantityField("Quantité");

        final FlowPane group = new FlowPane(Orientation.VERTICAL);
        group.setAlignment(Pos.CENTER);
        group.setVgap(10);

        group.getChildren().addAll(type, label, quantity);
        return group;
    }

    @Override
    public Node renderBottomContent() {
        final FormActions formActions = new FormActions("Ajouter", "Retour en arrière");
        formActions.setConfirmCallback(this::sendForm);
        formActions.setCancelCallback(event -> this.close());
        return formActions;
    }


}
