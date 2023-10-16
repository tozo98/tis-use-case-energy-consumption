package com.tis.usecase.energyconsumption.controller;

import com.tis.usecase.energyconsumption.converter.MeterRequestConverter;
import com.tis.usecase.energyconsumption.converter.MeterResponseConverter;
import com.tis.usecase.energyconsumption.domain.MeterEntity;
import com.tis.usecase.energyconsumption.domain.MeterRequest;
import com.tis.usecase.energyconsumption.domain.MeterResponse;
import com.tis.usecase.energyconsumption.domain.MeterWithSingleMonthResponse;
import com.tis.usecase.energyconsumption.service.MeterHandlerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MeterHandlerRestControllerTest {

    @Mock
    private MeterHandlerService meterHandlerServiceMock;

    @Mock
    private MeterRequestConverter meterRequestConverterMock;

    @Mock
    private MeterResponseConverter meterResponseConverterMock;

    @InjectMocks
    private MeterHandlerRestController underTest;

    @Test
    public void testSaveAll() {
        MeterRequest request = new MeterRequest();
        request.setMeterId(1L);
        MeterEntity entity = new MeterEntity();
        entity.setId(1L);
        when(meterRequestConverterMock.convert(any())).thenReturn(entity);

        underTest.saveAll(List.of(request));

        verify(meterRequestConverterMock).convert(any());
        verify(meterHandlerServiceMock).saveAll(any());
    }

    @Test
    public void testFindById() {
        MeterEntity entity = new MeterEntity();
        entity.setId(42L);
        when(meterHandlerServiceMock.findById(any())).thenReturn(entity);
        MeterResponse response = new MeterResponse();
        response.setId(42L);
        when(meterResponseConverterMock.convert(any())).thenReturn(response);

        MeterResponse result = underTest.findById(42L);

        assertNotNull(result);
        assertEquals(42L, result.getId().longValue());
        verify(meterHandlerServiceMock).findById(any());
        verify(meterResponseConverterMock).convert(any());
    }

    @Test
    public void testFindByIdAndMonth() {
        MeterEntity entity = new MeterEntity();
        entity.setId(42L);
        when(meterHandlerServiceMock.findById(any())).thenReturn(entity);
        MeterWithSingleMonthResponse response = new MeterWithSingleMonthResponse();
        response.setId(42L);
        when(meterResponseConverterMock.convertToSingleMonthResponse(any(), anyString())).thenReturn(response);

        MeterWithSingleMonthResponse result = underTest.findByIdAndMonth(42L, "JAN");

        assertNotNull(result);
        assertEquals(42L, result.getId().longValue());
        verify(meterHandlerServiceMock).findById(any());
        verify(meterResponseConverterMock).convertToSingleMonthResponse(any(), anyString());
    }


}