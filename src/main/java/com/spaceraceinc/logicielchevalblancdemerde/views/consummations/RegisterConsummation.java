package com.spaceraceinc.logicielchevalblancdemerde.views.consummations;

import com.spaceraceinc.logicielchevalblancdemerde.MainMenu;
import com.spaceraceinc.logicielchevalblancdemerde.modules.Consummation;
import com.spaceraceinc.logicielchevalblancdemerde.modules.CustomerConsummation;
import com.spaceraceinc.logicielchevalblancdemerde.utils.Utils;
import com.spaceraceinc.logicielchevalblancdemerde.modals.AddConsummationModal;
import com.spaceraceinc.logicielchevalblancdemerde.ui.StageTemplate;
import com.spaceraceinc.logicielchevalblancdemerde.ui.fields.CustomButton;
import com.spaceraceinc.logicielchevalblancdemerde.ui.fields.CustomQuantityField;
import com.spaceraceinc.logicielchevalblancdemerde.ui.FormActions;
import com.spaceraceinc.logicielchevalblancdemerde.ui.typography.Title;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;

import java.util.ArrayList;


public class RegisterConsummation extends StageTemplate {

    private CustomQuantityField chamberNumber;

    private final ListProperty<Consummation> consummations;

    public RegisterConsummation() {
        super("Enregistrer des consommations", 350, 250);

        ObservableList<Consummation> observableList = FXCollections.observableArrayList(new ArrayList<>());

        this.consummations = new SimpleListProperty<>(observableList);
        this.setResizable(false);

        this.consummations.addListener((props, oldValue, newValue) -> {
            this.updateScene();
        });
    }

    private void registerConsummation(ActionEvent action) {
        int chamberNumber = this.chamberNumber.getField().getValue();
        if(!Utils.isIntFieldValid(this.consummations.size())) {
            this.openAlert(Alert.AlertType.ERROR, "Veuillez ajouter au moins une consommation");
            return;
        }
        if(!Utils.isIntFieldValid(chamberNumber)) {
            this.openAlert(Alert.AlertType.ERROR, "Vous n'avez pas renseigner un numéro de chambre supérieur à 1.");
            return;
        }

        this.close();
        this.openAlert(Alert.AlertType.INFORMATION, "Les consommations ont été ajoutés à la chambre.");

        CustomerConsummation customerConsummation = new CustomerConsummation(chamberNumber, consummations.stream().toList());
        MainMenu.CUSTOMER_CONSUMMATIONS_LIST.add(customerConsummation);
    }

    @Override
    public FlowPane renderMainContent() {
        final FlowPane group = new FlowPane(Orientation.VERTICAL);
        final Button button = new CustomButton("Ajouter une consommation");
        this.chamberNumber = new CustomQuantityField("Numéro de chambre", "Format: NuméroEtageXX (ex: 201)");

        button.setOnAction(action -> this.openModal(new AddConsummationModal(this.consummations)));

        group.setAlignment(Pos.CENTER);
        group.setVgap(10);
        group.getChildren().addAll(this.chamberNumber, button);
        if(this.consummations != null && this.consummations.size() > 0) {
            group.getChildren().add(new Title("Liste des consommations ajoutées (" + this.consummations.size() + ")"));
            this.consummations.forEach(consummation ->
                group.getChildren().add(consummation.render(event ->
                    this.consummations.remove(consummation))
                )
            );
            this.sizeToScene();
        }
        return group;
    }

    @Override
    public Node renderTopContent() {
        return new Title("Enregistrer des consommations");
    }

    @Override
    public HBox renderBottomContent() {
        final FormActions formActions = new FormActions("Enregistrer", "Retour en arrière");
        formActions.setConfirmCallback(this::registerConsummation);
        formActions.setCancelCallback(event -> this.close());
        return formActions;
    }

}
