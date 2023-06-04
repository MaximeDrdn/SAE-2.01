package com.spaceraceinc.logicielchevalblancdemerde.modules;

import java.io.*;
import java.time.LocalDate;

public class CustomerServices implements Serializable {

    private int chamberNumber;
    private LocalDate registrationDate;
    private String choice;
    private String label;

    public CustomerServices(int chamberNumber, String choice) {
        this.chamberNumber = chamberNumber;
        this.choice = choice;
        this.label = null;
        this.registrationDate = LocalDate.now();
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setChamberNumber(int chamberNumber) {
        this.chamberNumber = chamberNumber;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public int getChamberNumber() {
        return chamberNumber;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public String getChoice() {
        return choice;
    }

    public String getLabel() {
        return label;
    }

    @Serial
    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
        oos.writeObject(this);
    }

    @Serial
    private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
        ois.defaultReadObject();
        CustomerServices customerPresentations = (CustomerServices) ois.readObject();
        this.setChamberNumber(customerPresentations.getChamberNumber());
        this.setChoice(customerPresentations.getChoice());
        this.setLabel(customerPresentations.getLabel());
        this.setRegistrationDate(customerPresentations.getRegistrationDate());
    }

}
