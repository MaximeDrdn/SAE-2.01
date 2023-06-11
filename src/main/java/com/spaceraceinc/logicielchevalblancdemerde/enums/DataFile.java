package com.spaceraceinc.logicielchevalblancdemerde.enums;

public enum DataFile {

    CUSTOMER_PRESTATIONS_DATA("customersPrestations"),
    CUSTOMER_CONSUMMATIONS_DATA("customersConsummations"),
    CUSTOMER_BREAKFASTS_DATA("customersBreakfasts");
    
    private final String fileName;
    
    DataFile(final String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
