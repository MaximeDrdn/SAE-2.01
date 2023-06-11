package com.spaceraceinc.logicielchevalblancdemerde.views.prestations;

import com.spaceraceinc.logicielchevalblancdemerde.ui.StageTemplate;
import com.spaceraceinc.logicielchevalblancdemerde.ui.fields.CustomQuantityField;
import com.spaceraceinc.logicielchevalblancdemerde.ui.fields.CustomTextField;
import javafx.scene.Node;

public class EditPrestation extends StageTemplate {

    private CustomQuantityField chamberNumber;
    private CustomTextField libelle;

    public EditPrestation(){
        super("Modification d'une prestation");
    }

    @Override
    public Node renderTopContent() {
        return null;
    }

    @Override
    public Node renderMainContent() {
        return null;
    }

    @Override
    public Node renderBottomContent() {
        return null;
    }
}