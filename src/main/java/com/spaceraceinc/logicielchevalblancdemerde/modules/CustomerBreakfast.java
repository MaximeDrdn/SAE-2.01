package com.spaceraceinc.logicielchevalblancdemerde.modules;

import java.io.*;
import java.time.LocalDate;

public class CustomerBreakfast implements Serializable {

    private LocalDate registrationDate;
    private int chamberNumber;
    private String type;
    private int quantity;

    public CustomerBreakfast(int chamberNumber, String type, int quantity) {
        this.chamberNumber = chamberNumber;
        this.type = type;
        this.quantity = quantity;
        this.registrationDate = LocalDate.now();
    }

    public int getChamberNumber() {
        return chamberNumber;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getType() {
        return type;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setChamberNumber(int chamberNumber) {
        this.chamberNumber = chamberNumber;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setType(String type) {
        this.type = type;
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
        CustomerBreakfast customerBreakfast = (CustomerBreakfast) ois.readObject();
        this.setChamberNumber(customerBreakfast.getChamberNumber());
        this.setType(customerBreakfast.getType());
        this.setQuantity(customerBreakfast.getQuantity());
        this.setRegistrationDate(customerBreakfast.getRegistrationDate());
    }
}
