package com.example.logicielchevalblancdemerde.views;

import com.example.logicielchevalblancdemerde.Utils;
import com.example.logicielchevalblancdemerde.enums.ServiceType;
import com.example.logicielchevalblancdemerde.enums.TitleType;
import com.example.logicielchevalblancdemerde.ui.FormActions;
import com.example.logicielchevalblancdemerde.ui.StageTemplate;
import com.example.logicielchevalblancdemerde.ui.fields.CustomButton;
import com.example.logicielchevalblancdemerde.ui.fields.CustomQuantityField;
import com.example.logicielchevalblancdemerde.ui.fields.CustomRadioList;
import com.example.logicielchevalblancdemerde.ui.fields.CustomTextField;
import com.example.logicielchevalblancdemerde.ui.title.Title;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RegisterService extends StageTemplate {

    private CustomQuantityField chamberNumber;
    private CustomRadioList list;
    private CustomTextField label;
    private CustomQuantityField amountWF;

    private BooleanProperty hasSelectedPressing;

    private Title title;
    private FlowPane mainContent;
    private HBox bottomContent;

    public RegisterService() {
        super("Enregistrer des prestations à l'accueil", 400, 550);
    }

    private void loadServiceTypes() {
        ServiceType[] serviceTypes = ServiceType.values();
        for (int i = 0; i < serviceTypes.length; i++) {
            ServiceType type = serviceTypes[i];
            list.addButton(type.getName(), i == 0);
        }

        list.selectedToggleProperty().addListener((props, oldValue, newValue) -> {
            RadioButton button = (RadioButton) this.list.getSelectedToggle();
            this.hasSelectedPressing.set(button.getText().equals(ServiceType.PRESSING.getName()));
        });
    }

    private void showRecap(ActionEvent action) {
        final RadioButton selectedToggle = (RadioButton) this.list.getSelectedToggle();

        final int chamberNumber = this.chamberNumber.getField().getValue();
        final int amountWF = this.amountWF.getField().getValue();

        final String label = this.label.getField().getText();
        final String choice = selectedToggle.getText();

        final ArrayList<String> labels = new ArrayList<>(List.of("numéro de chambre", "montant HT"));
        final ArrayList<Boolean> values = new ArrayList<>(List.of(Utils.isIntFieldValid(chamberNumber), Utils.isIntFieldValid(amountWF)));


        if(choice.equals(ServiceType.PRESSING.getName())) {
            labels.add("libellé");
            values.add(Utils.isStringFieldValid(label));
        }


        final Map<String, Boolean> invalidFields = Utils.getInvalidFieldsFrom(labels, values);
        if(invalidFields != null && invalidFields.size() > 0) {
            this.openAlert(Alert.AlertType.ERROR, "Erreur: les champs suivants ne sont pas valides: " + String.join(", ", invalidFields.keySet()));
            return;
        }

        CustomButton exit = new CustomButton("Quitter");
        exit.setOnAction(event -> this.close());

        this.title.setText("Récapitulatif du service enregistré");
        this.updatePaneComponent(this.mainContent, this.renderRecap());
        this.updatePaneComponent(this.bottomContent, exit);
    }

    private FlowPane renderRecap() {
        final FlowPane group = new FlowPane(Orientation.VERTICAL);
        final RadioButton selectedOption = (RadioButton) this.list.getSelectedToggle();
        final ObservableList<Node> children = group.getChildren();

        children.addAll(
            new Title("Date d'enregistrement: " + Utils.formatDate(LocalDateTime.now()), TitleType.H2),
            new Title("Numéro de la chambre: " + chamberNumber.getField().getValue(), TitleType.H2),
            new Title("Type de prestation: " + selectedOption.getText(), TitleType.H2)
        );
        if(this.hasSelectedPressing.get())
            children.add( new Title("Libellé: " + this.label.getField().getText(), TitleType.H2));
        children.add(new Title("Montant HT: " + amountWF.getField().getValue(), TitleType.H2));
        return group;
    }

    private FlowPane renderFields() {
        final FlowPane group = new FlowPane(Orientation.VERTICAL);
        final ObservableList<Node> children = group.getChildren();

        this.chamberNumber = new CustomQuantityField("Numéro de chambre");
        this.label = new CustomTextField("Libellé");
        this.amountWF = new CustomQuantityField("Montant HT");

        this.hasSelectedPressing = new SimpleBooleanProperty(false);

        this.list = new CustomRadioList();
        this.loadServiceTypes();

        children.add(chamberNumber);
        children.addAll(this.list.getButtons());
        this.hasSelectedPressing.addListener((props, oldValue, newValue) -> {
            if(newValue)
                children.add(this.label);
            else
                children.remove(this.label);
        });
        children.add(amountWF);
        return group;
    }

    private FormActions renderActions() {
        final FormActions formActions = new FormActions("Enregistrer", "Quitter");
        formActions.setConfirmCallback(this::showRecap);
        formActions.setCancelCallback(event -> this.close());
        return formActions;
    }

    @Override
    public Node renderTopContent() {
        if(this.title == null)
            this.title = new Title("Enregistrer un service à l'accueil");
        return null;
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
