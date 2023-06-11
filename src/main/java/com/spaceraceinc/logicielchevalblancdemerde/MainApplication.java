package com.spaceraceinc.logicielchevalblancdemerde;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class MainApplication extends Application {

    @Override
    public void start(Stage stage) {
        stage = new MainMenu();
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch();
    }
}