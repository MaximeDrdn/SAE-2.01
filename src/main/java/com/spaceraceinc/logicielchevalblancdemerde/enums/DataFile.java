package com.spaceraceinc.logicielchevalblancdemerde.enums;

public enum DataFile {

    CUSTOMER_SERVICES_DATA("customersServices");
    
    private final String fileName;
    
    DataFile(final String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
