package com.spaceraceinc.logicielchevalblancdemerde.ui;

import com.spaceraceinc.logicielchevalblancdemerde.enums.CustomFont;
import com.spaceraceinc.logicielchevalblancdemerde.enums.TitleType;
import javafx.scene.text.Text;

public class Title extends Text {

    @Deprecated
    public Title(String s, TitleType ignored) {
        super(s);
        this.setFont(CustomFont.GARAMOND_BOLD.getFont());
    }

    public Title(String s) {
        this(s, TitleType.H1);
    }

}
