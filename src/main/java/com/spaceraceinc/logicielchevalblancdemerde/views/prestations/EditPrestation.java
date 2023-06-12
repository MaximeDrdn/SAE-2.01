package com.spaceraceinc.logicielchevalblancdemerde.views.prestations;

import com.spaceraceinc.logicielchevalblancdemerde.ui.StageTemplate;
import com.spaceraceinc.logicielchevalblancdemerde.ui.fields.CustomQuantityField;
import com.spaceraceinc.logicielchevalblancdemerde.ui.fields.CustomTextField;
import javafx.scene.Node;


import com.spaceraceinc.logicielchevalblancdemerde.enums.DataFile;
import com.spaceraceinc.logicielchevalblancdemerde.enums.ServiceType;
import com.spaceraceinc.logicielchevalblancdemerde.modules.CustomerPrestation;
import com.spaceraceinc.logicielchevalblancdemerde.ui.FormActions;
import com.spaceraceinc.logicielchevalblancdemerde.ui.StageTemplate;
import com.spaceraceinc.logicielchevalblancdemerde.ui.fields.CustomButton;
import com.spaceraceinc.logicielchevalblancdemerde.ui.fields.CustomQuantityField;
import com.spaceraceinc.logicielchevalblancdemerde.ui.fields.CustomRadioList;
import com.spaceraceinc.logicielchevalblancdemerde.ui.fields.CustomTextField;
import com.spaceraceinc.logicielchevalblancdemerde.ui.typography.Title;
import com.spaceraceinc.logicielchevalblancdemerde.utils.FileManager;
import com.spaceraceinc.logicielchevalblancdemerde.utils.Utils;
import javafx.beans.Observable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EditPrestation extends StageTemplate {
    private CustomQuantityField chamberNumber;
    private CustomRadioList list;
    private CustomTextField libellePressing;
    private CustomQuantityField montantHT;
    private BooleanProperty hasSelectedPressing;

    public EditPrestation(){
        super("Modification d'une prestation", 350, 400);
        this.setResizable(false);
        this.setMinWidth(350);
        this.setMinHeight(400);
        this.getIcons().add(new Image("file:resources/images/icon.png"));
    }

    public EditPrestation(String stageTitle, int defaultWidth, int defaultHeight) {
        super(stageTitle, defaultWidth, defaultHeight);
    }

    @Override
    public Node renderTopContent() {
        VBox haut = new VBox();

        Title modif = new Title("Modifier un service à l'accueil");

        haut.getChildren().add(modif);
        haut.setPadding(new Insets(10, 0, 20, 0));
        return null;
    }

    private void loadServiceTypes() {
        ServiceType[] serviceTypes = ServiceType.values();
        for (int i = 0; i < serviceTypes.length; i++) {
            ServiceType type = serviceTypes[i];
            this.list = new CustomRadioList();
            list.addButton(type.getName(), i == 0);
            list.addButton(ServiceType.NAVETTE.getName());
        }

        list.selectedToggleProperty().addListener((props, oldValue, newValue) -> {
            RadioButton button = (RadioButton) this.list.getSelectedToggle();
            //BooleanProperty hasSelectedPressing = new SimpleBooleanProperty();
            this.hasSelectedPressing.set(button.getText().equals(ServiceType.PRESSING.getName()));
        });
    }

    private void registerService (ActionEvent action) {
        final RadioButton selectedToggle = (RadioButton) this.list.getSelectedToggle();

        final int chamberNumber = this.chamberNumber.getField().getValue();
        final int montantHT = this.montantHT.getField().getValue();

        final String libellePressing = this.libellePressing.getField().getText();
        final String choice = selectedToggle.getText();

        final ArrayList<String> labels = new ArrayList<>(List.of("numéro de chambre", "montant HT"));
        final ArrayList<Boolean> values = new ArrayList<>(List.of(Utils.isIntFieldValid(chamberNumber), Utils.isIntFieldValid(montantHT)));
        final boolean hasPressing = choice.equals(ServiceType.PRESSING.getName());


        if(hasPressing) {
            labels.add("libellé");
            values.add(Utils.isStringFieldValid(libellePressing));
        }


        final Map<String, Boolean> invalidFields = Utils.getInvalidFieldsFrom(labels, values);
        if(invalidFields != null && invalidFields.size() > 0) {
            this.openAlert(Alert.AlertType.ERROR, "Erreur: les champs suivants ne sont pas valides: " + String.join(", ", invalidFields.keySet()));
            return;
        }

        this.close();
        this.openAlert(Alert.AlertType.INFORMATION, "Les prestations ont été ajoutés.");

        CustomerPrestation customerServices = new CustomerPrestation(chamberNumber, choice, montantHT);
        if(hasPressing)
            customerServices.setLabel(libellePressing);
        FileManager.writeFile(DataFile.CUSTOMER_PRESTATIONS_DATA.getFileName(), customerServices);
    }


    @Override
    public Node renderMainContent() {
        VBox root = new VBox();
        ObservableList<Node> children = root.getChildren();

        CustomQuantityField chamberNumber = new CustomQuantityField("Numéro de chambre");
        CustomRadioList list = new CustomRadioList();
        this.loadServiceTypes();
        CustomQuantityField montantHT = new CustomQuantityField("Montant HT ");
        this.libellePressing = new CustomTextField("Libéllé");
        BooleanProperty hasSelectedPressing = new SimpleBooleanProperty();

        children.add(chamberNumber);

        assert this.list != null;
        children.addAll(this.list.getButtons());

        this.hasSelectedPressing = new SimpleBooleanProperty(false);

        this.hasSelectedPressing.addListener((props, old_v, new_v) -> {
            if(new_v){
                children.add(this.libellePressing);
            } else {
                children.remove(this.libellePressing);
            }
        });

        children.add(montantHT);
        root.setSpacing(10);
        root.setPadding(new Insets(10, 0 , 0, 0));
        return root;
    }

    @Override
    public Node renderBottomContent() {
        HBox bas = new HBox();

        final FormActions formActions = new FormActions("Enregistrer", "Quitter");
        formActions.setConfirmCallback(this::registerService);
        formActions.setCancelCallback(event -> this.close());

        bas.getChildren().addAll(formActions);
        bas.setPadding(new Insets(20, 0, 0, 0 ));
        bas.setSpacing(10);

        return bas;
    }
}
