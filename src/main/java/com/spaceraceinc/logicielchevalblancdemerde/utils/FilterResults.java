package com.spaceraceinc.logicielchevalblancdemerde.utils;


import com.spaceraceinc.logicielchevalblancdemerde.modules.CustomerServices;

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
            .filter(result -> Utils.dateEquals(((CustomerServices) result).getRegistrationDate(), this.date))
            .collect(Collectors.toList());
    }

}
