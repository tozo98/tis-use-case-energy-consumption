package com.tis.usecase.energyconsumption.converter;

import org.junit.jupiter.api.Test;

import java.time.DateTimeException;

import static org.junit.jupiter.api.Assertions.*;

class MonthConverterTest {

    @Test
    public void testHappyPathWhenInputIsUpperCase(){
        int result = new MonthConverter().convert("APR");
        assertEquals(4, result);
    }

    @Test
    public void testConvertWhenInputIsLowerCase(){
        int result = new MonthConverter().convert("apr");
        assertEquals(4, result);
    }

    @Test
    public void testConvertWhenInputIsMixedCase(){
        int result = new MonthConverter().convert("aPr");
        assertEquals(4, result);
    }

    @Test
    public void testConvertWhenInputIsLongerThanExpected(){
        int result = new MonthConverter().convert("APRIL");
        assertEquals(0, result);
    }

    @Test
    public void testConvertWhenInputIsEmpty(){
        int result = new MonthConverter().convert("");
        assertEquals(0, result);
    }

    @Test
    public void testConvertWhenInputIsNull(){
        int result = new MonthConverter().convert(null);
        assertEquals(0, result);
    }

    @Test
    public void testAsMonthStringMethod(){
        String result = new MonthConverter().asMonthString(1);
        assertEquals("JAN", result);
    }

    @Test
    public void testAsMonthStringMethodWhenInputIsInvalid(){
        assertThrows(DateTimeException.class, () -> new MonthConverter().asMonthString(0));
        assertThrows(DateTimeException.class, () -> new MonthConverter().asMonthString(13));
    }

}