package com.spaceraceinc.logicielchevalblancdemerde.views;

import com.spaceraceinc.logicielchevalblancdemerde.Utils;
import com.spaceraceinc.logicielchevalblancdemerde.modals.AddConsummationModal;
import com.spaceraceinc.logicielchevalblancdemerde.modules.Consummation;
import com.spaceraceinc.logicielchevalblancdemerde.ui.StageTemplate;
import com.spaceraceinc.logicielchevalblancdemerde.ui.fields.CustomButton;
import com.spaceraceinc.logicielchevalblancdemerde.ui.fields.CustomQuantityField;
import com.spaceraceinc.logicielchevalblancdemerde.ui.FormActions;
import com.spaceraceinc.logicielchevalblancdemerde.ui.Title;
import com.spaceraceinc.logicielchevalblancdemerde.enums.TitleType;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;

import java.time.LocalDateTime;
import java.util.ArrayList;


public class RegisterConsummation extends StageTemplate {

    private CustomQuantityField chamberNumber;

    private FlowPane mainContent;
    private HBox bottomContent;

    private final ListProperty<Consummation> consummations;

    public RegisterConsummation() {
        super("Enregistrer des consommations");

        ObservableList<Consummation> observableList = FXCollections.observableArrayList(new ArrayList<>());

        this.consummations = new SimpleListProperty<>(observableList);
    }

    private void showRecap(ActionEvent action) {
        int chamberNumber = this.chamberNumber.getField().getValue();
        if(!Utils.isIntFieldValid(this.consummations.size())) {
            this.openAlert(Alert.AlertType.ERROR, "Veuillez ajouter au moins une consommation");
            return;
        }
        if(!Utils.isIntFieldValid(chamberNumber)) {
            this.openAlert(Alert.AlertType.ERROR, "Vous n'avez pas renseigner un numéro de chambre supérieur à 1.");
            return;
        }

        this.updatePaneComponent(this.mainContent, this.renderRecap());

        final CustomButton exit = new CustomButton("Quitter");
        exit.setOnAction(event -> this.close());
        this.updatePaneComponent(this.bottomContent, exit);
    }

    private FlowPane renderRecap() {
        FlowPane group = new FlowPane(Orientation.VERTICAL);

        group.getChildren().addAll(
            new Title("Date d'enregistrement: " + Utils.formatDate(LocalDateTime.now()), TitleType.H2),
            new Title("Numéro de la chambre: " + chamberNumber.getField().getValue(), TitleType.H3),
            new Title("Consomamtions:", TitleType.H4)
        );
        this.consummations.forEach(consummation ->
            group.getChildren().add(consummation.render())
        );
        return group;
    }

    private FlowPane renderFields() {
        final FlowPane group = new FlowPane(Orientation.VERTICAL);
        final Button button = new Button("Ajouter une consommation");
        this.chamberNumber = new CustomQuantityField("Numéro de chambre", "Format: NuméroEtageXX (ex: 201)");

        button.setOnAction(action -> this.openModal(new AddConsummationModal(this.consummations)));

        group.setVgap(10);
        group.getChildren().addAll(this.chamberNumber, button);
        return group;
    }

    @Override
    public FlowPane renderMainContent() {
        if(this.mainContent == null)
            this.mainContent = this.renderFields();
        return this.mainContent;
    }

    @Override
    public Node renderTopContent() {
        return new Title("Enregistrer des consommations");
    }

    @Override
    public HBox renderBottomContent() {
        if (this.bottomContent == null) {
            final FormActions formActions = new FormActions("Enregistrer", "Quitter");
            formActions.setConfirmCallback(this::showRecap);
            formActions.setCancelCallback(event -> this.close());
            this.bottomContent = formActions;
        }
        return this.bottomContent;
    }

}
