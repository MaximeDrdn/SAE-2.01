package com.spaceraceinc.logicielchevalblancdemerde;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Utils {

    public static String formatDate(LocalDateTime date) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd à HH:mm");
        return dtf.format(date);
    }

    public static boolean isStringFieldValid(String value) {
        return !value.trim().equals("");
    }

    public static boolean isIntFieldValid(int value) {
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
