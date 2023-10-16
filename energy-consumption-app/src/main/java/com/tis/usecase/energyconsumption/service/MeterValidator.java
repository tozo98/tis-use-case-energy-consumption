package com.tis.usecase.energyconsumption.service;

import com.tis.usecase.energyconsumption.domain.MeterEntity;
import com.tis.usecase.energyconsumption.domain.ProfileEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeterValidator {

    public void validateProfileForMeterEntity(MeterEntity meterEntity) {

    }

    public void validateMeterReadingValues(List<ProfileEntity> profiles, List<MeterEntity> meters) {
        // TODO
    }
}
