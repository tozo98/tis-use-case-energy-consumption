package com.tis.usecase.energyconsumption.converter;

import com.tis.usecase.energyconsumption.domain.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MeterResponseConverterTest {

    @Mock
    private MonthConverter monthConverter;

    @InjectMocks
    private MeterResponseConverter underTest;

    @Test
    public void testConvertMethod() {
        ProfileEntity profile = new ProfileEntity();
        profile.setName("profile");
        MeterEntity entity = new MeterEntity();
        entity.setId(42L);
        entity.setProfile(profile);
        MeterReadingEntity meterReading = new MeterReadingEntity();
        meterReading.setReading(10.0);
        meterReading.setMonth(1);
        meterReading.setConsumption(10.0);
        meterReading.setMeter(entity);
        entity.setMeterReadings(List.of(meterReading));
        when(monthConverter.asMonthString(anyInt())).thenReturn("JAN");

        MeterResponse result = underTest.convert(entity);

        assertNotNull(result);
        assertNotNull(result.getProfileName());
        assertNotNull(result.getTotalConsumption());
        assertEquals(entity.getProfile().getName(), result.getProfileName());
        assertNotNull(result.getMeterReadings());
        assertFalse(result.getMeterReadings().isEmpty());
        assertEquals(meterReading.getReading().doubleValue(), result.getMeterReadings().get(0).getReading().doubleValue());
        verify(monthConverter).asMonthString(anyInt());
    }

    @Test
    public void testSingleMonthResponseConverterMethod() {
        ProfileEntity profile = new ProfileEntity();
        profile.setName("profile");
        MeterEntity entity = new MeterEntity();
        entity.setId(42L);
        entity.setProfile(profile);
        MeterReadingEntity meterReading = new MeterReadingEntity();
        meterReading.setReading(10.0);
        meterReading.setMonth(1);
        meterReading.setConsumption(10.0);
        meterReading.setMeter(entity);
        entity.setMeterReadings(List.of(meterReading));
        when(monthConverter.convert(anyString())).thenReturn(1);

        MeterWithSingleMonthResponse result = underTest.convertToSingleMonthResponse(entity, "JAN");

        assertNotNull(result);
        assertNotNull(result.getMonth());
        assertNotNull(result.getReading());
        assertNotNull(result.getConsumption());
        assertNotNull(result.getId());
        assertEquals(42L, result.getId().longValue());
        assertEquals("JAN", result.getMonth());
        assertEquals(10.0, result.getConsumption().doubleValue());
        assertEquals(10.0, result.getReading().doubleValue());
        verify(monthConverter).convert(anyString());
    }

}