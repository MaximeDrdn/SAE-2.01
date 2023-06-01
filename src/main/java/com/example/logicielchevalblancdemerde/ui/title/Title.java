package com.example.logicielchevalblancdemerde.ui.title;

import com.example.logicielchevalblancdemerde.enums.TitleType;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Title extends Text {

    public Title(String s, TitleType titleType) {
        super(s);

        this.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, titleType.getFontSize()));
    }

    public Title(String s) {
        this(s, TitleType.H1);
    }

}
