package com.tis.usecase.energyconsumption.converter;

import com.tis.usecase.energyconsumption.domain.MeterEntity;
import com.tis.usecase.energyconsumption.domain.MeterReadingResponse;
import com.tis.usecase.energyconsumption.domain.MeterResponse;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class MeterResponseConverter implements Converter<MeterEntity, MeterResponse> {

    private MonthConverter monthConverter;

    @Override
    public MeterResponse convert(MeterEntity meterEntity) {
        MeterResponse response = new MeterResponse();
        response.setId(meterEntity.getId());
        response.setProfileName(meterEntity.getProfile().getName());
        List<MeterReadingResponse> meterReadingResponses = meterEntity.getMeterReadings().stream()
                .map(meterReadingEntity -> {
                    MeterReadingResponse meterReadingResponse = new MeterReadingResponse();
                    meterReadingResponse.setReading(meterReadingEntity.getReading());
                    meterReadingResponse.setMonth(monthConverter.asMonthString(meterReadingEntity.getMonth()));
                    meterReadingResponse.setConsumption(meterReadingEntity.getConsumption());
                    return meterReadingResponse;
                }).collect(Collectors.toList());
        response.setMeterReadings(meterReadingResponses);
        response.setTotalConsumption(meterReadingResponses.stream().mapToDouble(MeterReadingResponse::getConsumption).sum());
        return response;
    }
}
