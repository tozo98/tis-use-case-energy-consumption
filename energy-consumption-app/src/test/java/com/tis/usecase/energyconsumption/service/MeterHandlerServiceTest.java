package com.tis.usecase.energyconsumption.service;

import com.tis.usecase.energyconsumption.domain.FractionEntity;
import com.tis.usecase.energyconsumption.domain.MeterEntity;
import com.tis.usecase.energyconsumption.domain.MeterReadingEntity;
import com.tis.usecase.energyconsumption.domain.ProfileEntity;
import com.tis.usecase.energyconsumption.exception.MeterReadingValidationException;
import com.tis.usecase.energyconsumption.exception.ProfileNotFoundException;
import com.tis.usecase.energyconsumption.repository.MeterRepository;
import com.tis.usecase.energyconsumption.repository.ProfileRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MeterHandlerServiceTest {

    @Mock
    private ProfileRepository profileRepositoryMock;

    @Mock
    private MeterRepository meterRepositoryMock;

    @Mock
    private MeterValidator meterValidatorMock;

    @InjectMocks
    private MeterHandlerService underTest;

    @Test
    public void testValidateProfileWhenProfileIsPresent() {
        MeterEntity meterEntity = new MeterEntity();
        meterEntity.setProfileName("profile-A");
        ProfileEntity profileEntity = new ProfileEntity();
        profileEntity.setName("profile-A");
        when(profileRepositoryMock.findByName(any())).thenReturn(List.of(profileEntity));

        assertDoesNotThrow(() -> underTest.setProfileForMeterEntity(meterEntity));

        assertEquals(profileEntity.getName(), meterEntity.getProfile().getName());
        verify(profileRepositoryMock).findByName(any());
    }

    @Test
    public void testValidateProfileWhenProfileIsNotPresent() {
        MeterEntity meterEntity = new MeterEntity();
        meterEntity.setProfileName("profile-A");
        ProfileEntity profileEntity = new ProfileEntity();
        profileEntity.setName("profile-B");
        when(profileRepositoryMock.findByName(any())).thenReturn(new ArrayList<>());

        assertThrows(ProfileNotFoundException.class, () -> underTest.setProfileForMeterEntity(meterEntity));

        assertNull(meterEntity.getProfile());
        verify(profileRepositoryMock).findByName(any());
    }

    @Test
    public void testValidateMetersWhenReadingsAreValid() {
        MeterEntity meterEntity = new MeterEntity();
        MeterReadingEntity meterReading1 = new MeterReadingEntity();
        meterReading1.setReading(1.0);
        meterReading1.setMonth(1);
        MeterReadingEntity meterReading2 = new MeterReadingEntity();
        meterReading2.setReading(10.0);
        meterReading2.setMonth(2);
        MeterReadingEntity meterReading3 = new MeterReadingEntity();
        meterReading3.setReading(12.0);
        meterReading3.setMonth(3);
        MeterReadingEntity meterReading4 = new MeterReadingEntity();
        meterReading4.setReading(15.0);
        meterReading4.setMonth(4);
        meterEntity.setMeterReadings(List.of(meterReading1, meterReading2, meterReading3, meterReading4));
        assertDoesNotThrow(() -> underTest.calculateConsumption(List.of(new ProfileEntity()), List.of(meterEntity)));
        assertEquals(1.0, meterReading1.getConsumption().doubleValue());
        assertEquals(9.0, meterReading2.getConsumption().doubleValue());
        assertEquals(2.0, meterReading3.getConsumption().doubleValue());
        assertEquals(3.0, meterReading4.getConsumption().doubleValue());

    }

    @Test
    public void testValidateMetersWhenReadingsAreNotValid() {
        MeterEntity meterEntity = new MeterEntity();
        MeterReadingEntity meterReading1 = new MeterReadingEntity();
        meterReading1.setReading(1.0);
        meterReading1.setMonth(1);
        MeterReadingEntity meterReading2 = new MeterReadingEntity();
        meterReading2.setReading(2.0);
        meterReading2.setMonth(2);
        MeterReadingEntity meterReading3 = new MeterReadingEntity();
        meterReading3.setReading(1.0);
        meterReading3.setMonth(3);
        MeterReadingEntity meterReading4 = new MeterReadingEntity();
        meterReading4.setReading(2.0);
        meterReading4.setMonth(4);
        meterEntity.setMeterReadings(List.of(meterReading1, meterReading2, meterReading3, meterReading4));
        assertThrows(MeterReadingValidationException.class, () -> underTest.calculateConsumption(List.of(new ProfileEntity()), List.of(meterEntity)));
    }

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