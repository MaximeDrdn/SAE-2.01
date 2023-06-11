package com.spaceraceinc.logicielchevalblancdemerde.views.consummations;

import com.spaceraceinc.logicielchevalblancdemerde.enums.ServiceType;
import com.spaceraceinc.logicielchevalblancdemerde.modules.CustomerConsummation;
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

public class ConsummationDetails extends StageTemplate {

    private final CustomerConsummation customerConsummation;

    public ConsummationDetails(CustomerConsummation customerConsummation) {
        super("Récapitulatif d'une consommation", 350, 400);
        this.setResizable(false);

        this.customerConsummation = customerConsummation;
        this.updateScene();
    }

    @Override
    public Node renderTopContent() {
        return new Title("Récapitulatif d'une consommation");
    }

    @Override
    public Node renderMainContent() {
        VBox root = new VBox();
        if(this.customerConsummation == null)
            return null;

        root.getChildren().addAll(
                new Label("Numéro de chambre: "+this.customerConsummation.getChamberNumber()),
                new Text("Date d'enregistrement: " + Utils.formatDate(this.customerConsummation.getRegistrationDate())),
                new Title("Liste des consommations:")
        );
        this.customerConsummation.getConsummations().forEach(consummation -> root.getChildren().add(consummation.render()));

        root.setSpacing(15);
        root.setPadding(new Insets((10)));
        this.sizeToScene();

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