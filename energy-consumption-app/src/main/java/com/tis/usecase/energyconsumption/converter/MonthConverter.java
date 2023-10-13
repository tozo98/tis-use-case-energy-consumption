package com.tis.usecase.energyconsumption.converter;

import org.springframework.stereotype.Component;

import java.time.Month;

@Component
public class MonthConverter {

    public int convert(String input) {
        int result = 0;
        for(Month month : Month.values()) {
            if(month.toString().substring(0,3).equals(input)) {
                result = month.getValue();
            }
        }
        return result;
    }
}
