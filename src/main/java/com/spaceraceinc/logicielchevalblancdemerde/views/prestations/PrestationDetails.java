package com.spaceraceinc.logicielchevalblancdemerde.views.prestations;

import com.spaceraceinc.logicielchevalblancdemerde.enums.ServiceType;
import com.spaceraceinc.logicielchevalblancdemerde.modules.CustomerPrestation;
import com.spaceraceinc.logicielchevalblancdemerde.ui.StageTemplate;
import com.spaceraceinc.logicielchevalblancdemerde.ui.fields.CustomButton;
import com.spaceraceinc.logicielchevalblancdemerde.ui.typography.Title;
import com.spaceraceinc.logicielchevalblancdemerde.utils.Utils;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class PrestationDetails extends StageTemplate {

    private final CustomerPrestation customerPrestation;

    public PrestationDetails(CustomerPrestation customerPrestation) {
        super("Récapitulatif d'une préstation", 350, 400);
        this.setResizable(false);

        this.customerPrestation = customerPrestation;
        this.updateScene();
    }

    @Override
    public Node renderTopContent() {
        return new Title("Récapitulatif d'une préstation");
    }

    @Override
    public Node renderMainContent() {
        VBox root = new VBox();
        if(this.customerPrestation == null)
            return null;
        Label numberChamber = new Label("Numéro de chambre: "+this.customerPrestation.getChamberNumber());
        Label typePrestation = new Label("Type de prestation: "+this.customerPrestation.getChoice());
        if(this.customerPrestation.getChoice().equals(ServiceType.PRESSING.getName()))
            root.getChildren().addAll(new Label(this.customerPrestation.getLabel()));
        Label montantHT = new Label("Montant HT: "+this.customerPrestation.getAmountWF()+"€");
        Text textDate = new Text("Date d'enregistrement: " + Utils.formatDate(customerPrestation.getRegistrationDate()));

        root.getChildren().addAll(numberChamber, textDate, typePrestation, montantHT);
        root.setSpacing(15);
        root.setPadding(new Insets((10)));

        return root;
    }

    @Override
    public Node renderBottomContent() {
        FlowPane bottomRoot = new FlowPane();
        CustomButton quit = new CustomButton("Quitter");
        quit.setOnAction( e -> {
            this.close();
        });
        bottomRoot.getChildren().addAll(quit);
        bottomRoot.setAlignment(Pos.BOTTOM_RIGHT);
        return bottomRoot;
    }
}