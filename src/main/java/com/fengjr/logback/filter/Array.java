package com.fengjr.logback.filter;

import java.util.ArrayList;
import java.util.List;

public class Array {
    private List<String> values = new ArrayList<String>();

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }

    public void addValue(String value){
        values.add(value);
    }
}
