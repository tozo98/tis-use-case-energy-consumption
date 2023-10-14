package com.tis.usecase.energyconsumption.converter;

import com.tis.usecase.energyconsumption.domain.ProfileEntity;
import com.tis.usecase.energyconsumption.domain.ProfileRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Month;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProfileConverterTest {

    @Mock
    private MonthConverter monthConverter;

    @InjectMocks
    private ProfileConverter underTest;

    @Test
    public void testConvertMethodHappyPath() {
        when(monthConverter.convert(any())).thenReturn(4);
        ProfileRequest request = new ProfileRequest();
        request.setName("request");
        Map<String, Double> fractions = new HashMap<>();
        fractions.put("APR", 0.3);
        request.setFractions(fractions);

        ProfileEntity result = underTest.convert(request);

        assertNotNull(result);
        assertEquals(request.getName(), result.getName());
        assertNotNull(result.getFractions());
        assertEquals(request.getFractions().size(), result.getFractions().size());
        assertEquals(Month.APRIL.getValue(), result.getFractions().get(0).getMonth().intValue());
        assertEquals(request.getFractions().get("APR"), result.getFractions().get(0).getValue());
    }

}