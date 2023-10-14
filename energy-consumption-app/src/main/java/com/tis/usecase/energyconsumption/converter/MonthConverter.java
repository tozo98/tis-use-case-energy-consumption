package com.tis.usecase.energyconsumption.converter;

import org.springframework.stereotype.Component;

import java.time.Month;

@Component
public class MonthConverter {

    public int convert(String input) {
        int result = 0;
        if (input == null) {
            return result;
        }
        for (Month month : Month.values()) {
            if (month.toString().substring(0, 3).equals(input.toUpperCase())) {
                result = month.getValue();
            }
        }
        return result;
    }

    public String asMonthString(int input) {
        return Month.of(input).toString().substring(0, 3);
    }
}
