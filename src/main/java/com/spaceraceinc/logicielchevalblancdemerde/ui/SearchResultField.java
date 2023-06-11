package com.spaceraceinc.logicielchevalblancdemerde.ui;

import com.spaceraceinc.logicielchevalblancdemerde.MainMenu;
import com.spaceraceinc.logicielchevalblancdemerde.enums.NavLink;
import com.spaceraceinc.logicielchevalblancdemerde.utils.Utils;
import com.spaceraceinc.logicielchevalblancdemerde.enums.CustomColor;
import com.spaceraceinc.logicielchevalblancdemerde.enums.CustomFont;
import com.spaceraceinc.logicielchevalblancdemerde.ui.fields.CustomButton;
import com.spaceraceinc.logicielchevalblancdemerde.ui.typography.Title;
import com.spaceraceinc.logicielchevalblancdemerde.views.prestations.EditPrestation;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.time.LocalDate;

public class SearchResultField extends BorderPane {

    private NavLink linkData;
    private Object data;

    public final record SearchFieldData(int chamberNumber, LocalDate date) {}

    public SearchResultField(MainMenu window, Object data) {
        this.data = data;
        this.linkData = window.getActiveNavLink();

        this.setStyle("-fx-border-width: 1px;-fx-border-color: " + CustomColor.BROWN.asString());
        this.setPadding(new Insets(10));

        GridPane pane2 = new GridPane();
        CustomButton buttonDetail = new CustomButton("Voir en détail");
        buttonDetail.setOnAction(event -> window.openModal(this.linkData.getDetailsStage(data)));

        CustomButton buttonModification = new CustomButton("Modifier");
        buttonModification.setOnAction(event -> window.openModal(new EditPrestation()));

        pane2.add(buttonDetail, 0, 0);
        pane2.add(buttonModification, 1, 0);
        pane2.setHgap(10);

        this.setLeft(this.renderDetails());
        this.setRight(pane2);
    }

    private GridPane renderDetails() {
        SearchFieldData fieldData = this.linkData.getSearchFieldData(data);
        GridPane pane = new GridPane();
        pane.add(new Title("Numéro de chambre: " + fieldData.chamberNumber()), 0, 0);
        pane.add(this.getRegistrationText(fieldData.date()), 0, 1);
        pane.setPadding(new Insets(0, 50, 0, 0));
        return pane;
    }

    private Text getRegistrationText(LocalDate dateTime) {
        Text text = new Text("Date d'enregistrement: "+Utils.formatDate(dateTime));
        text.setFont(CustomFont.MONTSERRAT_REGULAR.getFont());
        text.setFill(CustomColor.GREY.asColor());
        return text;
    }

}
