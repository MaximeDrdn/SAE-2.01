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

    private CustomButton addButton;
    private NavLink activeNavLink;
    private GridPane navbarComponent;

    public MainMenu() {
        super("Logiciel ChevalBlanc", 1000, 600);
        this.setMinWidth(1000);
        this.setMinHeight(600);
    }

    private GridPane renderNavBar() {
        if (this.activeNavLink == null)
            this.activeNavLink = NavLink.first();

        GridPane group = new GridPane();
        NavLink[] values = NavLink.values();
        for (int i = 0; i < values.length; i++) {
            NavLink link = values[i];
            String linkName = link.getName();

            final CustomNavLinkItem linkItem = new CustomNavLinkItem(linkName, this.activeNavLink.getName().equals(linkName));
            linkItem.setOnAction(event -> {
                this.addButton.setText(link.getButtonLabel());
                this.activeNavLink = link;
                this.updatePaneComponent(this.navbarComponent, this.renderNavBar());
                this.addButton.setOnAction(__ -> this.openModal(NavLink.getClassFrom(linkName)));
            });

            group.add(linkItem, i, 0);
        }
        final CustomButton exit = new CustomButton("Quitter");

        exit.setOnAction(event -> this.close());
        group.add(exit, 3, 0);
        group.setHgap(10);

        group.setAlignment(Pos.CENTER);
        return group;
    }

    private FlowPane renderHeader() {
        final CustomButton searchButton = new CustomButton(new CustomImage("icons/search.png", 14, 14));
        final NavLink activeLink = this.activeNavLink;
        final String buttonLabel = activeLink.getButtonLabel();
        final String name = activeLink.getName();

        if (this.addButton == null)
            this.addButton = new CustomButton(buttonLabel);
        final FlowPane pane = new FlowPane();

        this.addButton.setOnAction(event -> this.openModal(NavLink.getClassFrom(name)));

        searchButton.setTranslateY(11);
        this.addButton.setTranslateY(11);

        pane.getChildren().addAll(
            new CustomTextField("Libellé"),
            new CustomDateField("Date d'enregistrement"),
            searchButton,
            this.addButton
        );
        pane.setAlignment(Pos.CENTER);
        pane.setHgap(10);
        pane.setPadding(new Insets(25, 0, 30, 0));
        return pane;
    }

    private FlowPane renderResultFields() {
        FlowPane pane = new FlowPane(Orientation.VERTICAL);
        //pane.getChildren().add(new Title("Aucun resultat trouvé"));
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
        if(this.navbarComponent == null)
            this.navbarComponent = this.renderNavBar();

        BorderPane pane = new BorderPane();
        pane.setLeft(new CustomImage("logo.png", 140, 50));
        pane.setRight(this.navbarComponent);
        pane.setPadding(new Insets(10, 20, 10, 20));
        return pane;
    }

    @Override
    public Node renderBottomContent() {
        final HBox group = new HBox();
        final Title title = new Title("© ChevalBlanc", false);

        title.setFill(CustomColor.BROWN.asColor());
        group.getChildren().add(title);
        group.setAlignment(Pos.CENTER);
        return group;
    }

    @Override
    public BorderPane renderMainContent() {
        BorderPane pane = new BorderPane();

        pane.setTop(this.renderHeader());
        pane.setCenter(this.renderResultFields());
        return pane;
    }

}
