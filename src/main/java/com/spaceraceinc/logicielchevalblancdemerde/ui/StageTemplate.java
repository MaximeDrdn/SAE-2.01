package com.spaceraceinc.logicielchevalblancdemerde.ui;

import com.spaceraceinc.logicielchevalblancdemerde.enums.CustomColor;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public abstract class StageTemplate extends Stage {

    public StageTemplate(String stageTitle, int defaultWidth, int defaultHeight) {
        this.getIcons().add(new Image("file:resources/images/icon.png"));

        if(defaultWidth > 0 && defaultHeight > 0) {
            this.setWidth(defaultWidth);
            this.setHeight(defaultHeight);
        } else {
            this.sizeToScene();
        }

        this.setTitle(stageTitle);
        this.setScene(new Scene(this.getRoot()));
    }

    public StageTemplate(String stageTitle) {
        this(stageTitle, 0, 0);
    }

    public void updateScene() {
        this.setScene(new Scene(this.getRoot()));
    }

    public void updatePaneComponent(Pane component, Node newComponent) {
        final ObservableList<Node> children = component.getChildren();
        children.clear();
        children.addAll(newComponent);
    }

    public void openModal(StageTemplate modal) {
        modal.initOwner(this);
        modal.initModality(Modality.APPLICATION_MODAL);
        modal.showAndWait();
    }

    public Alert openAlert(Alert.AlertType alertType, String content) {
        Alert alert = new Alert(alertType);
        alert.setContentText(content);
        alert.initOwner(this);
        alert.show();

        return alert;
    }

    public Parent getRoot() throws NullPointerException {
        final BorderPane group = new BorderPane();
        group.setPadding(new Insets(10));

        group.setTop(this.renderTopContent());
        group.setCenter(this.renderMainContent());
        group.setBottom(this.renderBottomContent());
        return group;
    }

    public abstract Node renderTopContent();
    public abstract Node renderMainContent();
    public abstract Node renderBottomContent();

}
