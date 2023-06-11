package com.spaceraceinc.logicielchevalblancdemerde.views.breakfasts;

import com.spaceraceinc.logicielchevalblancdemerde.enums.ServiceType;
import com.spaceraceinc.logicielchevalblancdemerde.modules.CustomerBreakfast;
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

public class BreakfastDetails extends StageTemplate {

    private final CustomerBreakfast customerBreakfast;

    public BreakfastDetails(CustomerBreakfast customerBreakfast) {
        super("Récapitulatif d'un petit déjeuné ajouté", 350, 400);
        this.setResizable(false);

        this.customerBreakfast = customerBreakfast;
        this.updateScene();
    }

    @Override
    public Node renderTopContent() {
        return new Title("Récapitulatif d'un petit déjeuné ajouté");
    }

    @Override
    public Node renderMainContent() {
        VBox root = new VBox();
        if(this.customerBreakfast == null)
            return null;

        root.getChildren().addAll(
            new Label("Numéro de chambre: "+this.customerBreakfast.getChamberNumber()),
            new Text("Date d'enregistrement: " + Utils.formatDate(this.customerBreakfast.getRegistrationDate())),
            new Text("Quantité de petit-déjeuné: " + this.customerBreakfast.getQuantity()),
            new Text("Type de petit-déjeuné: " + this.customerBreakfast.getType())
        );
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