package com.spaceraceinc.logicielchevalblancdemerde.ui.fields;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CustomImage extends ImageView {

    public CustomImage(String imageName, int width, int height) {
        this.setImage(new Image("file:resources/images/"+imageName));
        this.setFitHeight(height);
        this.setFitWidth(width);
    }

}
