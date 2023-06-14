package com.spaceraceinc.logicielchevalblancdemerde;

import com.spaceraceinc.logicielchevalblancdemerde.enums.CustomColor;
import com.spaceraceinc.logicielchevalblancdemerde.enums.NavLink;
import com.spaceraceinc.logicielchevalblancdemerde.modules.CustomerBreakfast;
import com.spaceraceinc.logicielchevalblancdemerde.modules.CustomerConsummation;
import com.spaceraceinc.logicielchevalblancdemerde.modules.CustomerPrestation;
import com.spaceraceinc.logicielchevalblancdemerde.test.AddingTest;
import com.spaceraceinc.logicielchevalblancdemerde.ui.SearchResultField;
import com.spaceraceinc.logicielchevalblancdemerde.ui.fields.*;
import com.spaceraceinc.logicielchevalblancdemerde.ui.StageTemplate;
import com.spaceraceinc.logicielchevalblancdemerde.ui.typography.Title;
import com.spaceraceinc.logicielchevalblancdemerde.utils.FilterResults;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainMenu extends StageTemplate {

    public static SimpleListProperty<CustomerPrestation> CUSTOMER_PRESTATIONS_LIST = new SimpleListProperty<>(FXCollections.observableArrayList(new ArrayList<>()));
    public static SimpleListProperty<CustomerBreakfast> CUSTOMER_BREAKFAST_LIST = new SimpleListProperty<>(FXCollections.observableArrayList(new ArrayList<>()));
    public static SimpleListProperty<CustomerConsummation> CUSTOMER_CONSUMMATIONS_LIST = new SimpleListProperty<>(FXCollections.observableArrayList(new ArrayList<>()));

    private CustomButton addButton;
    private NavLink activeNavLink;
    private GridPane navbarComponent;

    private CustomDateField date;

    private ListProperty<Object> results;

    public MainMenu() {
        super("Logiciel ChevalBlanc", 1000, 600);
        this.setMinWidth(1000);
        this.setMinHeight(600);

        AddingTest.loadTestGame();
        this.updateScene();
        this.loadDataUpdateListeners();
    }

    private void loadDataUpdateListeners() {
        CUSTOMER_PRESTATIONS_LIST.addListener((props, oldValue, newValue) -> {
            this.updateFilteredResults(this.date.getField().getValue(), Arrays.asList(newValue.toArray()));
        });
        CUSTOMER_BREAKFAST_LIST.addListener((props, oldValue, newValue) -> {
            this.updateFilteredResults(this.date.getField().getValue(), Arrays.asList(newValue.toArray()));
        });
        CUSTOMER_CONSUMMATIONS_LIST.addListener((props, oldValue, newValue) -> {
            this.updateFilteredResults(this.date.getField().getValue(), Arrays.asList(newValue.toArray()));
        });
    }

    private void search(ActionEvent event) {
        DatePicker date = this.date.getField();
        LocalDate dateContent = date.getValue();

        if(dateContent == null) {
            this.openAlert(Alert.AlertType.ERROR, "La date n'est pas remplit.");
            return;
        }
        this.updateFilteredResults(dateContent, this.getActiveLinkAssociatedResults());
    }

    private List<Object> getActiveLinkAssociatedResults() {
        if(this.activeNavLink.getName().equals(NavLink.FEATURE1.getName()))
            return Arrays.asList(CUSTOMER_PRESTATIONS_LIST.toArray());
        else if(this.activeNavLink.getName().equals(NavLink.FEATURE2.getName()))
            return Arrays.asList(CUSTOMER_CONSUMMATIONS_LIST.toArray());
        else if(this.activeNavLink.getName().equals(NavLink.FEATURE3.getName()))
            return Arrays.asList(CUSTOMER_BREAKFAST_LIST.toArray());
        return null;
    }

    private void updateFilteredResults(LocalDate date, List<Object> results) {
        if(date == null) {
            this.updateResults(results);
            return;
        }
        FilterResults filterResults = new FilterResults(date);
        filterResults.setResults(results);
        this.updateResults(filterResults.getFilteredResults());
    }

    private void updateResults(List<Object> results) {
        this.results.clear();
        this.results.addAll(results);
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
            linkItem.setOnAction(event -> this.updatePage(link));

            group.add(linkItem, i, 0);
        }
        final CustomButton exit = new CustomButton("Quitter");

        exit.setOnAction(event -> this.close());
        group.add(exit, 3, 0);
        group.setHgap(10);

        group.setAlignment(Pos.CENTER);
        return group;
    }

    private void updatePage(NavLink link) {
        this.addButton.setText(link.getButtonLabel());
        this.activeNavLink = link;
        this.updatePaneComponent(this.navbarComponent, this.renderNavBar());
        this.addButton.setOnAction(__ -> this.openModal(link.getRegisterStage()));
        this.updateFilteredResults(this.date.getField().getValue(), this.getActiveLinkAssociatedResults());
    }

    private FlowPane renderHeader() {
        final CustomButton searchButton = new CustomButton(new CustomImage("icons/search.png", 14, 14));
        final NavLink activeLink = this.activeNavLink;
        final String buttonLabel = activeLink.getButtonLabel();

        this.addButton = new CustomButton(buttonLabel);
        this.date = new CustomDateField("Date d'enregistrement");

        final FlowPane pane = new FlowPane();

        this.addButton.setOnAction(event -> this.openModal(activeLink.getRegisterStage()));
        searchButton.setOnAction(this::search);

        searchButton.setTranslateY(11);
        this.addButton.setTranslateY(11);

        pane.getChildren().addAll(this.date, searchButton, this.addButton);
        pane.setAlignment(Pos.CENTER);
        pane.setHgap(10);
        pane.setPadding(new Insets(25, 0, 30, 0));
        return pane;
    }

    private FlowPane renderResultFields() {
        FlowPane pane = new FlowPane(Orientation.VERTICAL);
        this.results = new SimpleListProperty<>(FXCollections.observableArrayList(new ArrayList<>()));

        this.results.addListener((props, oldList, list) -> {
            pane.getChildren().clear();
            if(list.size() < 1)
                pane.getChildren().add(new Title("Aucun resultat trouvé."));
            else
                list.forEach(data -> pane.getChildren().add(new SearchResultField(this, data)));
        });

        this.updateResults(this.getActiveLinkAssociatedResults());
        pane.setVgap(20);
        pane.setAlignment(Pos.TOP_CENTER);
        return pane;
    }

    @Override
    public Node renderTopContent() {
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

    public NavLink getActiveNavLink() {
        return activeNavLink;
    }
}
