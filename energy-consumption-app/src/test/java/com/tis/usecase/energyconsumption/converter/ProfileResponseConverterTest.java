package com.tis.usecase.energyconsumption.converter;

import com.tis.usecase.energyconsumption.domain.FractionEntity;
import com.tis.usecase.energyconsumption.domain.ProfileEntity;
import com.tis.usecase.energyconsumption.domain.ProfileResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProfileResponseConverterTest {

    @Mock
    private MonthConverter monthConverter;

    @InjectMocks
    private ProfileResponseConverter underTest;

    @Test
    public void testConverterMethod() {
        when(monthConverter.asMonthString(anyInt())).thenReturn("APR");
        ProfileEntity entity = new ProfileEntity();
        entity.setName("profile-name");
        entity.setFractions(List.of(new FractionEntity(42L, entity, 4, 0.5)));

        ProfileResponse result = underTest.convert(entity);

        assertNotNull(result);
        assertEquals(entity.getName(), result.getName());
        assertNotNull(result.getFractions());
        assertEquals(entity.getFractions().size(),result.getFractions().size());
        assertTrue(result.getFractions().containsKey("APR"));
        assertEquals(entity.getFractions().get(0).getValue(), result.getFractions().get("APR"));
        verify(monthConverter).asMonthString(anyInt());
    }
}