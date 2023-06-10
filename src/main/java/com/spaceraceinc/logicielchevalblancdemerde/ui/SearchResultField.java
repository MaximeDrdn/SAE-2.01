package com.spaceraceinc.logicielchevalblancdemerde.ui;

import com.spaceraceinc.logicielchevalblancdemerde.utils.Utils;
import com.spaceraceinc.logicielchevalblancdemerde.enums.CustomColor;
import com.spaceraceinc.logicielchevalblancdemerde.enums.CustomFont;
import com.spaceraceinc.logicielchevalblancdemerde.ui.fields.CustomButton;
import com.spaceraceinc.logicielchevalblancdemerde.ui.typography.Title;
import com.spaceraceinc.logicielchevalblancdemerde.views.PrestationDetails;
import com.spaceraceinc.logicielchevalblancdemerde.views.PrestationModification;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.time.LocalDateTime;

public class SearchResultField extends BorderPane {

    public SearchResultField(StageTemplate window, Object data) {
        //CustomerServices customerServices = (CustomerServices) data;
        this.setStyle("-fx-border-width: 1px;-fx-border-color: " + CustomColor.BROWN.asString());
        this.setPadding(new Insets(10));

        GridPane pane = new GridPane();
        pane.add(new Title("Numéro de chambre: 2030"), 0, 0);
        pane.add(this.getRegistrationText(LocalDateTime.now()), 0, 1);
        pane.setPadding(new Insets(0, 50, 0, 0));

        GridPane pane2 = new GridPane();

        CustomButton buttonDetail = new CustomButton("Voir en détail");
        buttonDetail.setOnAction(event -> window.openModal(new PrestationDetails()));
        pane2.add(buttonDetail, 0, 0);
        CustomButton buttonModification = new CustomButton("Modifier");
        buttonModification.setOnAction(event -> window.openModal(new PrestationModification()));
        pane2.add(buttonModification, 1, 0);
        pane2.setHgap(10);

        this.setLeft(pane);
        this.setRight(pane2);
    }

    private Text getRegistrationText(LocalDateTime dateTime) {
        Text text = new Text("Date d'enregistrement: "+Utils.formatDate(dateTime));
        text.setFont(CustomFont.MONTSERRAT_REGULAR.getFont());
        text.setFill(CustomColor.GREY.asColor());
        return text;
    }

}
