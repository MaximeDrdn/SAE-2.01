package com.spaceraceinc.logicielchevalblancdemerde.modules;

import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.io.*;
import java.time.LocalDate;
import java.util.List;

public class CustomerConsummation implements Serializable {

    private int chamberNumber;
    private List<Consummation> consummations;
    private LocalDate registrationDate;

    public CustomerConsummation(int chamberNumber, List<Consummation> consummations) {
        this.chamberNumber = chamberNumber;
        this.consummations = consummations;
        this.registrationDate = LocalDate.now();
    }

    public int getChamberNumber() {
        return chamberNumber;
    }

    public List<Consummation> getConsummations() {
        return consummations;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setChamberNumber(int chamberNumber) {
        this.chamberNumber = chamberNumber;
    }

    public void setConsummations(List<Consummation> consummations) {
        this.consummations = consummations;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    @Serial
    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
        oos.writeObject(this);
    }

    @Serial
    private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
        ois.defaultReadObject();
        CustomerConsummation customerConsummations = (CustomerConsummation) ois.readObject();
        this.setChamberNumber(customerConsummations.getChamberNumber());
        this.setConsummations(customerConsummations.getConsummations());
        this.setRegistrationDate(customerConsummations.getRegistrationDate());
    }

}
