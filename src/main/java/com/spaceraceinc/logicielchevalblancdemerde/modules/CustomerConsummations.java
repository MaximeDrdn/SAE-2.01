package com.spaceraceinc.logicielchevalblancdemerde.modules;

import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.io.Serializable;

public class CustomerConsummations implements Serializable {

    public CustomerConsummations() {

    }

    private final record Consummation(String type, String label, int quantity) {
        public GridPane render() {
            GridPane group = new GridPane();

            group.add(new Text("Type de prestation: " + this.type), 0, 0);
            group.add(new Text("Libellé: " + this.label), 0, 1);
            group.add(new Text("Quantité: " + this.quantity), 0, 2);
            return group;
        }
    }

}
