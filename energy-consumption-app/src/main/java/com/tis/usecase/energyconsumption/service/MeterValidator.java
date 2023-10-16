package com.tis.usecase.energyconsumption.service;

import com.tis.usecase.energyconsumption.domain.FractionEntity;
import com.tis.usecase.energyconsumption.domain.MeterEntity;
import com.tis.usecase.energyconsumption.domain.MeterReadingEntity;
import com.tis.usecase.energyconsumption.domain.ProfileEntity;
import com.tis.usecase.energyconsumption.exception.MeterReadingValidationException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MeterValidator {

    public void validateConsumptionBasedOnFractions(List<MeterEntity> meters) {
        meters.forEach(meterEntity -> {
            Map<Integer, FractionEntity> fractions = new HashMap<>();
            meterEntity.getProfile().getFractions().forEach(fraction -> {
                fractions.put(fraction.getMonth(), fraction);
            });
            Double sum = meterEntity.getMeterReadings().stream().mapToDouble(MeterReadingEntity::getConsumption).sum();
            meterEntity.getMeterReadings().forEach(meterReading -> {
                Double consumption = meterReading.getConsumption();
                Double fraction = fractions.get(meterReading.getMonth()).getValue();
                validateConsumptionBasedOnFractions(sum, fraction, consumption);
            });
        });
    }

    void validateConsumptionBasedOnFractions(Double totalConsumption, Double fraction, Double actualConsumption) {
        if (Double.compare(actualConsumption, fraction * totalConsumption * 0.75) < 0 || Double.compare(actualConsumption, fraction * totalConsumption * 1.25) > 0) {
            throw new MeterReadingValidationException("Meter reading is not valid based on the given fraction");
        }
    }


}
