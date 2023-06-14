package com.spaceraceinc.logicielchevalblancdemerde.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Utils {

    public static String formatDate(LocalDate date) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return date.format(dtf);
    }

    public static boolean dateEquals(LocalDate dateA, LocalDate dateB) {
        return dateA.getYear() == dateB.getYear() &&
            dateA.getMonthValue() == dateB.getMonthValue() &&
            dateA.getDayOfMonth() == dateB.getDayOfMonth();
    }

    public static boolean isStringFieldValid(String value) {
        return !value.trim().equals("");
    }

    public static boolean isIntFieldValid(int value) {
        return value > 0;
    }

    public static boolean isDoubleFieldValid(double value) {
        return value > 0;
    }

    public static Map<String, Boolean> getInvalidFieldsFrom(List<String> fieldKeys, List<Boolean> fieldValues) {
        final Map<String, Boolean> fields = new HashMap<>();
        if(fieldKeys.size() != fieldValues.size()) {
            System.err.println("Les clés et les valeurs ne sont pas égales");
            return null;
        }
        for (int i = 0; i < fieldKeys.size(); i++)
            fields.put(fieldKeys.get(i), fieldValues.get(i));

        return fields.entrySet().stream()
                .filter(field -> !field.getValue())
                .collect(Collectors.toMap(Map.Entry::getKey, stringBooleanEntry -> false));
    }
}
