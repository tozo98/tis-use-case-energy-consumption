package com.tis.usecase.energyconsumption.converter;

import com.tis.usecase.energyconsumption.domain.MeterEntity;
import com.tis.usecase.energyconsumption.domain.MeterReadingEntity;
import com.tis.usecase.energyconsumption.domain.MeterRequest;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class MeterRequestConverter implements Converter<MeterRequest, MeterEntity> {

    private MonthConverter monthConverter;

    @Override
    public MeterEntity convert(MeterRequest meterRequest) {
        MeterEntity meterEntity = new MeterEntity();
        meterEntity.setId(meterRequest.getMeterId());
        meterEntity.setProfileName(meterRequest.getProfileName());
        List<MeterReadingEntity> meterReadings = new ArrayList<>();
        meterRequest.getReadings().forEach(
                (key, value) -> {
                    MeterReadingEntity meterReading = new MeterReadingEntity();
                    meterReading.setReading(value);
                    meterReading.setMonth(monthConverter.convert(key));
                    meterReading.setMeter(meterEntity);
                    meterReadings.add(meterReading);
                }
        );
        meterEntity.setMeterReadings(meterReadings);
        return meterEntity;
    }
}
