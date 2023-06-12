package com.spaceraceinc.logicielchevalblancdemerde.views.prestations;

import com.spaceraceinc.logicielchevalblancdemerde.enums.CustomColor;
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
    private CustomButton quit;

    public PrestationDetails(CustomerPrestation customerPrestation) {
        super("Récapitulatif d'une préstation", 350, 400);
        this.setResizable(false);

        this.customerPrestation = customerPrestation;
        this.updateScene();
    }

    @Override
    public Node renderTopContent() {
        VBox haut = new VBox();

        Title recap = new Title("Récapitulatif d'une préstation");

        haut.getChildren().add(recap);
        haut.setPadding(new Insets(20, 0, 20, 0));
        return haut;
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
        root.setStyle("-fx-border-width: 1px;-fx-border-color: " + CustomColor.BROWN.asString());
        root.setSpacing(15);
        root.setPadding(new Insets((10)));

        return root;
    }

    @Override
    public Node renderBottomContent() {
        VBox bas = new VBox();
        FlowPane bottomRoot = new FlowPane();
        quit = new CustomButton("Quitter");
        quit.setOnAction( e -> {
            this.close();
        });
        bottomRoot.getChildren().addAll(quit);
        bottomRoot.setAlignment(Pos.BOTTOM_RIGHT);
        bas.getChildren().add(bottomRoot);
        bas.setPadding(new Insets (20, 0, 0, 0));
        return bas;
    }
}
