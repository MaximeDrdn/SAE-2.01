package com.spaceraceinc.logicielchevalblancdemerde.views.consummations;

import com.spaceraceinc.logicielchevalblancdemerde.MainMenu;
import com.spaceraceinc.logicielchevalblancdemerde.modals.AddConsummationModal;
import com.spaceraceinc.logicielchevalblancdemerde.modules.Consummation;
import com.spaceraceinc.logicielchevalblancdemerde.modules.CustomerConsummation;
import com.spaceraceinc.logicielchevalblancdemerde.ui.FormActions;
import com.spaceraceinc.logicielchevalblancdemerde.ui.StageTemplate;
import com.spaceraceinc.logicielchevalblancdemerde.ui.fields.CustomButton;
import com.spaceraceinc.logicielchevalblancdemerde.ui.fields.CustomQuantityField;
import com.spaceraceinc.logicielchevalblancdemerde.ui.typography.Title;
import com.spaceraceinc.logicielchevalblancdemerde.utils.Utils;
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

public class EditConsummation extends StageTemplate {

    private CustomQuantityField chamberNumber;
    private CustomerConsummation data;

    private final ListProperty<Consummation> consummations;

    public EditConsummation(CustomerConsummation data) {
        super("Modifier des consommations", 350, 250);

        ObservableList<Consummation> observableList = FXCollections.observableArrayList(data.getConsummations());

        this.consummations = new SimpleListProperty<>(observableList);
        this.setResizable(false);

        this.consummations.addListener((props, oldValue, newValue) -> {
            this.updateScene();
        });

        this.data = data;
        this.updateScene();
    }

    private void registerConsummation(ActionEvent action) {
        if(!Utils.isIntFieldValid(this.consummations.size())) {
            this.openAlert(Alert.AlertType.ERROR, "Veuillez ajouter au moins une consommation");
            return;
        }

        this.close();
        this.openAlert(Alert.AlertType.INFORMATION, "Les consommations de la chambre ont été modifiées.");

        // @todo modifier ici
        CustomerConsummation customerConsummation = new CustomerConsummation(this.data.getChamberNumber(), consummations.stream().toList());
        MainMenu.CUSTOMER_CONSUMMATIONS_LIST.remove(this.data);
        MainMenu.CUSTOMER_CONSUMMATIONS_LIST.add(customerConsummation);
    }

    @Override
    public FlowPane renderMainContent() {
        if(this.data == null)
            return null;
        final FlowPane group = new FlowPane(Orientation.VERTICAL);
        final Button button = new CustomButton("Ajouter une consommation");

        button.setOnAction(action -> this.openModal(new AddConsummationModal(this.consummations)));

        group.setAlignment(Pos.CENTER);
        group.setVgap(10);
        group.getChildren().addAll(button);
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
        final FormActions formActions = new FormActions("Modifier", "Retour en arrière");
        formActions.setConfirmCallback(this::registerConsummation);
        formActions.setCancelCallback(event -> this.close());
        return formActions;
    }


}
