package com.spaceraceinc.logicielchevalblancdemerde.views.prestations;

import com.spaceraceinc.logicielchevalblancdemerde.MainMenu;
import com.spaceraceinc.logicielchevalblancdemerde.ui.StageTemplate;
import com.spaceraceinc.logicielchevalblancdemerde.ui.fields.*;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;


import com.spaceraceinc.logicielchevalblancdemerde.enums.ServiceType;
import com.spaceraceinc.logicielchevalblancdemerde.modules.CustomerPrestation;
import com.spaceraceinc.logicielchevalblancdemerde.ui.FormActions;
import com.spaceraceinc.logicielchevalblancdemerde.ui.fields.CustomQuantityField;
import com.spaceraceinc.logicielchevalblancdemerde.ui.fields.CustomTextField;
import com.spaceraceinc.logicielchevalblancdemerde.ui.typography.Title;
import com.spaceraceinc.logicielchevalblancdemerde.utils.Utils;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.FlowPane;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EditPrestation extends StageTemplate {

    private CustomQuantityField chamberNumber;
    private CustomRadioList list;
    private CustomTextField label;
    private CustomPriceField amountWF;

    private final CustomerPrestation data;

    public EditPrestation(CustomerPrestation data) {
        super("Modifier le service à l'accueil", 350, 450);
        this.setResizable(false);

        this.data = data;
        this.updateScene();
    }

    private void loadServiceTypes() {
        ServiceType[] serviceTypes = ServiceType.values();
        for (ServiceType type : serviceTypes) {
            list.addButton(type.getName(), this.data.getChoice().equals(type.getName()));
        }
    }

    private void registerService(ActionEvent action) {
        final RadioButton selectedToggle = (RadioButton) this.list.getSelectedToggle();

        double amountWF = 0;
        try {
            amountWF = this.amountWF.getField().getValue();
        } catch(NullPointerException ignored) {}

        final String label = this.label.getField().getText();
        final String choice = selectedToggle.getText();

        final ArrayList<String> labels = new ArrayList<>(List.of("montant HT"));
        final ArrayList<Boolean> values = new ArrayList<>(List.of(Utils.isDoubleFieldValid(amountWF)));
        final boolean hasPressing = choice.equals(ServiceType.PRESSING.getName());


        if(hasPressing) {
            labels.add("libellé");
            values.add(Utils.isStringFieldValid(label));
        }


        final Map<String, Boolean> invalidFields = Utils.getInvalidFieldsFrom(labels, values);
        if(invalidFields != null && invalidFields.size() > 0) {
            this.openAlert(Alert.AlertType.ERROR, "Erreur: les champs suivants ne sont pas valides: " + String.join(", ", invalidFields.keySet()));
            return;
        }

        this.close();
        this.openAlert(Alert.AlertType.INFORMATION, "La prestation a été modifié.");

        CustomerPrestation customerServices = new CustomerPrestation(this.data.getChamberNumber(), choice, amountWF);
        if(hasPressing)
            customerServices.setLabel(label);
        MainMenu.CUSTOMER_PRESTATIONS_LIST.remove(this.data);
        MainMenu.CUSTOMER_PRESTATIONS_LIST.add(customerServices);
    }

    private FlowPane renderFields() {
        if(this.data == null)
            return null;

        final FlowPane group = new FlowPane(Orientation.VERTICAL);
        final ObservableList<Node> children = group.getChildren();

        this.label = new CustomTextField("Libellé");
        this.label.setValue(this.data.getLabel() == null ? "" : this.data.getLabel());

        this.amountWF = new CustomPriceField("Montant HT");
        this.amountWF.setValue(this.data.getAmountWF());

        this.list = new CustomRadioList();
        this.loadServiceTypes();

        children.addAll(this.list.getButtons());

        list.selectedToggleProperty().addListener((props, oldValue, newValue) -> {
            RadioButton button = (RadioButton) this.list.getSelectedToggle();
            if(button.getText().equals(ServiceType.PRESSING.getName()))
                children.add(this.label);
            else
                children.remove(this.label);
        });

        children.add(amountWF);
        if(this.data.getChoice().equals(ServiceType.PRESSING.getName()))
            children.add(this.label);

        group.setVgap(10);
        group.setAlignment(Pos.CENTER);
        return group;
    }

    private FormActions renderActions() {
        final FormActions formActions = new FormActions("Modifier", "Quitter");
        formActions.setConfirmCallback(this::registerService);
        formActions.setCancelCallback(event -> this.close());
        return formActions;
    }

    @Override
    public Node renderTopContent() {
        return new Title("Modifier le service à l'accueil");
    }

    @Override
    public Node renderMainContent() {
        return this.renderFields();
    }

    @Override
    public Node renderBottomContent() {
        return this.renderActions();
    }
}
