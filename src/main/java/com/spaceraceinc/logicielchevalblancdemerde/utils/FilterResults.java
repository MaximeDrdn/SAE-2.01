package com.spaceraceinc.logicielchevalblancdemerde.utils;


import com.spaceraceinc.logicielchevalblancdemerde.modules.CustomerBreakfast;
import com.spaceraceinc.logicielchevalblancdemerde.modules.CustomerConsummation;
import com.spaceraceinc.logicielchevalblancdemerde.modules.CustomerPrestation;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class FilterResults {

    private final LocalDate date;
    private List<Object> results;

    public FilterResults(LocalDate date) {
        this.date = date;
        this.results = null;
    }

    public void setResults(List<Object> results) {
        this.results = results;
    }

    public List<Object> getFilteredResults() {
        return this.results.stream()
            .filter(result -> {
                LocalDate registrationDate = LocalDate.now();
                if(result instanceof CustomerConsummation)
                    registrationDate = ((CustomerConsummation) result).getRegistrationDate();
                else if(result instanceof CustomerPrestation)
                    registrationDate = ((CustomerPrestation) result).getRegistrationDate();
                else if(result instanceof CustomerBreakfast)
                    registrationDate = ((CustomerBreakfast) result).getRegistrationDate();
                return Utils.dateEquals(registrationDate, this.date);
            })
            .collect(Collectors.toList());
    }

}
