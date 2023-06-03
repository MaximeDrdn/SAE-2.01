package com.spaceraceinc.logicielchevalblancdemerde;

import com.spaceraceinc.logicielchevalblancdemerde.enums.CustomColor;
import com.spaceraceinc.logicielchevalblancdemerde.enums.NavLink;
import com.spaceraceinc.logicielchevalblancdemerde.ui.SearchResultField;
import com.spaceraceinc.logicielchevalblancdemerde.ui.fields.*;
import com.spaceraceinc.logicielchevalblancdemerde.ui.StageTemplate;
import com.spaceraceinc.logicielchevalblancdemerde.ui.typography.Title;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class MainMenu extends StageTemplate {

    private String addButtonLabel;
    private String linkActive;

    public MainMenu() {
        super("Logiciel LeChevalBlanc", 1000, 600);
        this.setMinWidth(1000);
    }

    private GridPane renderNavBar() {
        if (this.linkActive == null)
            this.linkActive = "Liste des prestations";

        GridPane group = new GridPane();
        NavLink[] values = NavLink.values();
        for (int i = 0; i < values.length; i++) {
            String linkName = values[i].getName();
            final CustomNavLinkItem link = new CustomNavLinkItem(linkName, this.linkActive.equals(linkName));
            group.add(link, i, 0);
        }
        final CustomButton exit = new CustomButton("Quitter");

        exit.setOnAction(event -> this.close());
        group.add(exit, 3, 0);
        group.setHgap(10);

        group.setAlignment(Pos.CENTER);
        return group;
    }

    private FlowPane renderHeader(String addButtonLabel) {
        FlowPane pane = new FlowPane();
        pane.getChildren().addAll(
            new CustomTextField("Libellé"),
            new CustomDateField("Date d'enregistrement"),
            new CustomButton(new CustomImage("icons/search.png", 15, 15)),
            new CustomButton(addButtonLabel)
        );
        pane.setAlignment(Pos.CENTER);
        pane.setHgap(10);
        pane.setPadding(new Insets(25, 0, 30, 0));
        return pane;
    }

    private FlowPane renderResultFields() {
        FlowPane pane = new FlowPane(Orientation.VERTICAL);
        pane.getChildren().addAll(
                new SearchResultField(),
                new SearchResultField(),
                new SearchResultField()
        );
        pane.setVgap(20);
        pane.setAlignment(Pos.TOP_CENTER);
        return pane;
    }

    @Override
    public Node renderTopContent() {
        BorderPane pane = new BorderPane();
        pane.setLeft(new CustomImage("logo.png", 140, 50));
        pane.setRight(this.renderNavBar());
        pane.setPadding(new Insets(10, 20, 10, 20));
        return pane;
    }

    @Override
    public Node renderBottomContent() {
        final HBox group = new HBox();
        final Title title = new Title("© Le Cheval Blanc", false);

        title.setFill(CustomColor.BROWN.asColor());
        group.getChildren().add(title);
        group.setAlignment(Pos.CENTER);
        return group;
    }

    @Override
    public BorderPane renderMainContent() {
        if(this.addButtonLabel == null)
            this.addButtonLabel = NavLink.first().getName();
        BorderPane pane = new BorderPane();

        pane.setTop(this.renderHeader(this.addButtonLabel));
        pane.setCenter(this.renderResultFields());
        return pane;
    }

}
