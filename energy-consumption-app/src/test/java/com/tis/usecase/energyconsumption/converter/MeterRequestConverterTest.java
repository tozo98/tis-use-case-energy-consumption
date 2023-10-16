package com.tis.usecase.energyconsumption.converter;

import com.tis.usecase.energyconsumption.domain.MeterEntity;
import com.tis.usecase.energyconsumption.domain.MeterRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MeterRequestConverterTest {


    @Mock
    private MonthConverter monthConverter;

    @InjectMocks
    private MeterRequestConverter underTest;

    @Test
    public void testConvertMethod() {
        MeterRequest request = new MeterRequest();
        request.setMeterId(42L);
        request.setProfileName("profile-name");
        request.setReadings(Map.of("JAN", 12.0));
        when(monthConverter.convert(anyString())).thenReturn(1);

        MeterEntity result = underTest.convert(request);

        assertNotNull(result);
        assertEquals(request.getMeterId(), result.getId());
        assertEquals(request.getProfileName(), result.getProfileName());
        assertNotNull(result.getMeterReadings());
        assertFalse(result.getMeterReadings().isEmpty());
        assertEquals(request.getReadings().get("JAN").doubleValue(), result.getMeterReadings().get(0).getReading().doubleValue());
        verify(monthConverter).convert(anyString());
    }
}