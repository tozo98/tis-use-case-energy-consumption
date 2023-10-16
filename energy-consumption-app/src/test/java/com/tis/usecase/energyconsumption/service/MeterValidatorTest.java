package com.tis.usecase.energyconsumption.service;

import com.tis.usecase.energyconsumption.domain.FractionEntity;
import com.tis.usecase.energyconsumption.domain.MeterEntity;
import com.tis.usecase.energyconsumption.domain.MeterReadingEntity;
import com.tis.usecase.energyconsumption.domain.ProfileEntity;
import com.tis.usecase.energyconsumption.exception.MeterReadingValidationException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MeterValidatorTest {

    private MeterValidator underTest = new MeterValidator();

    @Test
    public void testValidationBasedOnFractionsShouldNotThrowException() {
        assertDoesNotThrow(() -> underTest.validateConsumptionBasedOnFractions(100.0, 0.2, 20.0));
        assertDoesNotThrow(() -> underTest.validateConsumptionBasedOnFractions(100.0, 0.2, 17.0));
        assertDoesNotThrow(() -> underTest.validateConsumptionBasedOnFractions(100.0, 0.2, 22.0));
        assertDoesNotThrow(() -> underTest.validateConsumptionBasedOnFractions(100.0, 0.2, 15.1));
        assertDoesNotThrow(() -> underTest.validateConsumptionBasedOnFractions(100.0, 0.2, 24.9));
    }

    @Test
    public void testValidationBasedOnFractionsShouldThrowException() {
        assertThrows(MeterReadingValidationException.class, () -> underTest.validateConsumptionBasedOnFractions(100.0, 0.2, 40.0));
        assertThrows(MeterReadingValidationException.class, () -> underTest.validateConsumptionBasedOnFractions(100.0, 0.2, 14.9999));
        assertThrows(MeterReadingValidationException.class, () -> underTest.validateConsumptionBasedOnFractions(100.0, 0.2, 25.0001));
    }

    @Test
    public void testValidateConsumptionBasedOnFractions() {
        ProfileEntity profile = new ProfileEntity();
        profile.setName("profile-name");
        FractionEntity fractionEntity = new FractionEntity();
        fractionEntity.setMonth(1);
        fractionEntity.setValue(0.25);
        fractionEntity.setProfile(profile);
        FractionEntity fractionEntity2 = new FractionEntity();
        fractionEntity2.setMonth(2);
        fractionEntity2.setValue(0.0);
        fractionEntity2.setProfile(profile);
        FractionEntity fractionEntity3 = new FractionEntity();
        fractionEntity3.setMonth(3);
        fractionEntity3.setValue(0.75);
        fractionEntity3.setProfile(profile);
        profile.setFractions(List.of(fractionEntity, fractionEntity2, fractionEntity3));

        MeterEntity meterEntity = new MeterEntity();
        meterEntity.setProfile(profile);
        MeterReadingEntity reading = new MeterReadingEntity();
        reading.setReading(10.0);
        reading.setMonth(1);
        reading.setMeter(meterEntity);
        reading.setConsumption(10.0);
        MeterReadingEntity reading2 = new MeterReadingEntity();
        reading2.setReading(10.0);
        reading2.setMonth(2);
        reading2.setMeter(meterEntity);
        reading2.setConsumption(0.0);
        MeterReadingEntity reading3 = new MeterReadingEntity();
        reading3.setReading(40.0);
        reading3.setMonth(3);
        reading3.setMeter(meterEntity);
        reading3.setConsumption(30.0);
        meterEntity.setMeterReadings(List.of(reading, reading2, reading3));
        assertDoesNotThrow(() -> underTest.validateConsumptionBasedOnFractions(List.of(meterEntity)));
    }
}