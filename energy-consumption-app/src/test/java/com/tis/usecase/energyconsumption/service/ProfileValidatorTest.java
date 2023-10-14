package com.tis.usecase.energyconsumption.service;

import com.tis.usecase.energyconsumption.domain.FractionEntity;
import com.tis.usecase.energyconsumption.domain.ProfileEntity;
import com.tis.usecase.energyconsumption.exception.InvalidProfileException;
import org.junit.jupiter.api.Test;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProfileValidatorTest {

    @Test
    public void testValidateMethodWhenProfileIsValid() {
        ProfileEntity entity = new ProfileEntity();
        List<FractionEntity> fractions = new ArrayList<>();
        fractions.add(new FractionEntity(1L, entity, Month.JANUARY.getValue(), 1.0));
        fractions.add(new FractionEntity(1L, entity, Month.FEBRUARY.getValue(), 0.0));
        fractions.add(new FractionEntity(1L, entity, Month.MARCH.getValue(), 0.0));
        fractions.add(new FractionEntity(1L, entity, Month.APRIL.getValue(), 0.0));
        fractions.add(new FractionEntity(1L, entity, Month.MAY.getValue(), 0.0));
        fractions.add(new FractionEntity(1L, entity, Month.JUNE.getValue(), 0.0));
        fractions.add(new FractionEntity(1L, entity, Month.JULY.getValue(), 0.0));
        fractions.add(new FractionEntity(1L, entity, Month.AUGUST.getValue(), 0.0));
        fractions.add(new FractionEntity(1L, entity, Month.SEPTEMBER.getValue(), 0.0));
        fractions.add(new FractionEntity(1L, entity, Month.OCTOBER.getValue(), 0.0));
        fractions.add(new FractionEntity(1L, entity, Month.NOVEMBER.getValue(), 0.0));
        fractions.add(new FractionEntity(1L, entity, Month.DECEMBER.getValue(), 0.0));
        entity.setFractions(fractions);
        assertDoesNotThrow(() -> new ProfileValidator().validate(List.of(entity)));
    }

    @Test
    public void testValidateMethodWhenProfileHasAMissingMonth() {
        ProfileEntity entity = new ProfileEntity();
        List<FractionEntity> fractions = new ArrayList<>();
        fractions.add(new FractionEntity(1L, entity, Month.JANUARY.getValue(), 1.0));
        fractions.add(new FractionEntity(1L, entity, Month.MARCH.getValue(), 0.0));
        fractions.add(new FractionEntity(1L, entity, Month.APRIL.getValue(), 0.0));
        fractions.add(new FractionEntity(1L, entity, Month.MAY.getValue(), 0.0));
        fractions.add(new FractionEntity(1L, entity, Month.JUNE.getValue(), 0.0));
        fractions.add(new FractionEntity(1L, entity, Month.JULY.getValue(), 0.0));
        fractions.add(new FractionEntity(1L, entity, Month.AUGUST.getValue(), 0.0));
        fractions.add(new FractionEntity(1L, entity, Month.SEPTEMBER.getValue(), 0.0));
        fractions.add(new FractionEntity(1L, entity, Month.OCTOBER.getValue(), 0.0));
        fractions.add(new FractionEntity(1L, entity, Month.NOVEMBER.getValue(), 0.0));
        fractions.add(new FractionEntity(1L, entity, Month.DECEMBER.getValue(), 0.0));
        entity.setFractions(fractions);
        assertThrows(InvalidProfileException.class, () -> new ProfileValidator().validate(List.of(entity)));
    }

    @Test
    public void testValidateMethodWhenSumIsLowerThanTheExpectedValue() {
        ProfileEntity entity = new ProfileEntity();
        List<FractionEntity> fractions = new ArrayList<>();
        fractions.add(new FractionEntity(1L, entity, Month.JANUARY.getValue(), 0.1));
        fractions.add(new FractionEntity(1L, entity, Month.FEBRUARY.getValue(), 0.1));
        fractions.add(new FractionEntity(1L, entity, Month.MARCH.getValue(), 0.0));
        fractions.add(new FractionEntity(1L, entity, Month.APRIL.getValue(), 0.0));
        fractions.add(new FractionEntity(1L, entity, Month.MAY.getValue(), 0.0));
        fractions.add(new FractionEntity(1L, entity, Month.JUNE.getValue(), 0.0));
        fractions.add(new FractionEntity(1L, entity, Month.JULY.getValue(), 0.0));
        fractions.add(new FractionEntity(1L, entity, Month.AUGUST.getValue(), 0.0));
        fractions.add(new FractionEntity(1L, entity, Month.SEPTEMBER.getValue(), 0.0));
        fractions.add(new FractionEntity(1L, entity, Month.OCTOBER.getValue(), 0.0));
        fractions.add(new FractionEntity(1L, entity, Month.NOVEMBER.getValue(), 0.0));
        fractions.add(new FractionEntity(1L, entity, Month.DECEMBER.getValue(), 0.3));
        entity.setFractions(fractions);
        assertThrows(InvalidProfileException.class, () -> new ProfileValidator().validate(List.of(entity)));
    }

    @Test
    public void testValidateMethodWhenSumIsHigherThanTheExpectedValue() {
        ProfileEntity entity = new ProfileEntity();
        List<FractionEntity> fractions = new ArrayList<>();
        fractions.add(new FractionEntity(1L, entity, Month.JANUARY.getValue(), 0.1));
        fractions.add(new FractionEntity(1L, entity, Month.FEBRUARY.getValue(), 0.1));
        fractions.add(new FractionEntity(1L, entity, Month.MARCH.getValue(), 5.0));
        fractions.add(new FractionEntity(1L, entity, Month.APRIL.getValue(), 0.0));
        fractions.add(new FractionEntity(1L, entity, Month.MAY.getValue(), 0.0));
        fractions.add(new FractionEntity(1L, entity, Month.JUNE.getValue(), 0.0));
        fractions.add(new FractionEntity(1L, entity, Month.JULY.getValue(), 0.0));
        fractions.add(new FractionEntity(1L, entity, Month.AUGUST.getValue(), 0.0));
        fractions.add(new FractionEntity(1L, entity, Month.SEPTEMBER.getValue(), 0.0));
        fractions.add(new FractionEntity(1L, entity, Month.OCTOBER.getValue(), 0.0));
        fractions.add(new FractionEntity(1L, entity, Month.NOVEMBER.getValue(), 0.0));
        fractions.add(new FractionEntity(1L, entity, Month.DECEMBER.getValue(), 0.3));
        entity.setFractions(fractions);
        assertThrows(InvalidProfileException.class, () -> new ProfileValidator().validate(List.of(entity)));
    }

    @Test
    public void testValidateMethodWhenInputContainsDuplicateMonth() {
        ProfileEntity entity = new ProfileEntity();
        List<FractionEntity> fractions = new ArrayList<>();
        fractions.add(new FractionEntity(1L, entity, Month.JANUARY.getValue(), 1.0));
        fractions.add(new FractionEntity(1L, entity, Month.JANUARY.getValue(), 0.0));
        fractions.add(new FractionEntity(1L, entity, Month.MARCH.getValue(), 0.0));
        fractions.add(new FractionEntity(1L, entity, Month.APRIL.getValue(), 0.0));
        fractions.add(new FractionEntity(1L, entity, Month.MAY.getValue(), 0.0));
        fractions.add(new FractionEntity(1L, entity, Month.JUNE.getValue(), 0.0));
        fractions.add(new FractionEntity(1L, entity, Month.JULY.getValue(), 0.0));
        fractions.add(new FractionEntity(1L, entity, Month.AUGUST.getValue(), 0.0));
        fractions.add(new FractionEntity(1L, entity, Month.SEPTEMBER.getValue(), 0.0));
        fractions.add(new FractionEntity(1L, entity, Month.OCTOBER.getValue(), 0.0));
        fractions.add(new FractionEntity(1L, entity, Month.NOVEMBER.getValue(), 0.0));
        fractions.add(new FractionEntity(1L, entity, Month.DECEMBER.getValue(), 0.0));
        entity.setFractions(fractions);
        assertThrows(InvalidProfileException.class, () -> new ProfileValidator().validate(List.of(entity)));
    }

}