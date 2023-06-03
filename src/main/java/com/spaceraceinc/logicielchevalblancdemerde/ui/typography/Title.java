package com.spaceraceinc.logicielchevalblancdemerde.ui.typography;

import com.spaceraceinc.logicielchevalblancdemerde.enums.CustomColor;
import com.spaceraceinc.logicielchevalblancdemerde.enums.CustomFont;
import com.spaceraceinc.logicielchevalblancdemerde.enums.TitleType;
import javafx.scene.text.Text;

public class Title extends Text {

    @Deprecated
    public Title(String s, TitleType ignored) {
        super(s);
    }

    public Title(String s) {
        this(s, false);
    }

    public Title(String s, boolean isBold) {
        super(s);
        this.setFont(isBold ? CustomFont.GARAMOND_BOLD.getFont() : CustomFont.GARAMOND_REGULAR.getFont());
        this.setFont(CustomFont.GARAMOND_BOLD.getFont());
        this.setFill(CustomColor.BROWN.asColor());
    }

}
