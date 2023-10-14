package com.tis.usecase.energyconsumption.controller;

import com.tis.usecase.energyconsumption.converter.ProfileConverter;
import com.tis.usecase.energyconsumption.converter.ProfileResponseConverter;
import com.tis.usecase.energyconsumption.domain.FractionEntity;
import com.tis.usecase.energyconsumption.domain.ProfileEntity;
import com.tis.usecase.energyconsumption.domain.ProfileRequest;
import com.tis.usecase.energyconsumption.domain.ProfileResponse;
import com.tis.usecase.energyconsumption.service.ProfileHandlerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProfileHandlerRestControllerTest {

    @Mock
    private ProfileHandlerService profileHandlerServiceMock;

    @Mock
    private ProfileConverter profileConverterMock;

    @Mock
    private ProfileResponseConverter profileResponseConverterMock;

    @InjectMocks
    private ProfileHandlerRestController underTest;

    @Test
    public void testRetrieveAllMethod() {
        ProfileEntity entity = new ProfileEntity();
        entity.setName("profile-name");
        entity.setFractions(List.of(new FractionEntity(42L, entity, 4, 0.5)));
        when(profileHandlerServiceMock.findAll()).thenReturn(List.of(entity));
        ProfileResponse response = new ProfileResponse();
        response.setName("profile-name");
        response.setFractions(Map.of("APR", 0.5));
        when(profileResponseConverterMock.convert(any())).thenReturn(response);

        List<ProfileResponse> result = underTest.findProfiles();

        assertNotNull(result);
        assertNotNull(result.get(0).getFractions());
        assertEquals(entity.getFractions().size(), result.get(0).getFractions().size());
        assertTrue(result.get(0).getFractions().containsKey("APR"));
        assertEquals(entity.getFractions().get(0).getValue(), result.get(0).getFractions().get("APR"));
        verify(profileHandlerServiceMock).findAll();
        verify(profileResponseConverterMock).convert(any());
    }

    @Test
    public void testSaveAllMethod() {
        when(profileConverterMock.convert(any())).thenReturn(new ProfileEntity());
        List<ProfileRequest> request = List.of(new ProfileRequest());

        underTest.saveAll(request);

        verify(profileHandlerServiceMock).saveAll(any());
        verify(profileConverterMock).convert(any());
    }

}