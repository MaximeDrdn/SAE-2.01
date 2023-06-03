package com.spaceraceinc.logicielchevalblancdemerde.modules;

import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class Consummation {

    private final String type;
    private final String label;
    private final int quantity;

    public Consummation(String type, String label, int quantity) {
        this.type = type;
        this.label = label;
        this.quantity = quantity;
    }

    public GridPane render() {
        GridPane group = new GridPane();

        group.add(new Text("Type de prestation: " + this.type), 0, 0);
        group.add(new Text("Libellé: " + this.label), 0, 1);
        group.add(new Text("Quantité: " + this.quantity), 0, 2);
        return group;
    }

}
