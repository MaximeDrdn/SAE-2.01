package com.spaceraceinc.logicielchevalblancdemerde.modules;

import java.io.*;
import java.time.LocalDate;

public class CustomerPrestation implements Serializable {

    private int chamberNumber;
    private LocalDate registrationDate;
    private String choice;
    private String label;
    private int amountWF;

    public CustomerPrestation(int chamberNumber, String choice, int amountWF) {
        this.chamberNumber = chamberNumber;
        this.choice = choice;
        this.label = null;
        this.amountWF = amountWF;
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

    public void setAmountWF(int amountWF) {
        this.amountWF = amountWF;
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

    public int getAmountWF() {
        return amountWF;
    }

    @Serial
    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
        oos.writeObject(this);
    }

    @Serial
    private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
        ois.defaultReadObject();
        CustomerPrestation customerPresentations = (CustomerPrestation) ois.readObject();
        this.setChamberNumber(customerPresentations.getChamberNumber());
        this.setChoice(customerPresentations.getChoice());
        this.setLabel(customerPresentations.getLabel());
        this.setRegistrationDate(customerPresentations.getRegistrationDate());
        this.setAmountWF(customerPresentations.getAmountWF());
    }

}
