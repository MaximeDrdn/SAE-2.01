package com.spaceraceinc.logicielchevalblancdemerde.modules;

import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.io.*;

public class Consummation implements Serializable {

    private String type;
    private String label;
    private int quantity;

    public Consummation(String type, String label, int quantity) {
        this.type = type;
        this.label = label;
        this.quantity = quantity;
    }

    public String getType() {
        return type;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getLabel() {
        return label;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public GridPane render() {
        GridPane group = new GridPane();

        group.add(new Text("Type de prestation: " + this.type), 0, 0);
        group.add(new Text("Libellé: " + this.label), 0, 1);
        group.add(new Text("Quantité: " + this.quantity), 0, 2);
        return group;
    }

    @Serial
    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
        oos.writeObject(this);
    }

    @Serial
    private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
        ois.defaultReadObject();
        Consummation consummation = (Consummation) ois.readObject();
        consummation.setLabel(consummation.getLabel());
        consummation.setQuantity(consummation.getQuantity());
        consummation.setType(consummation.getType());
    }
}
