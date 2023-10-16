package com.tis.usecase.energyconsumption.service;

import com.tis.usecase.energyconsumption.domain.MeterEntity;
import com.tis.usecase.energyconsumption.domain.ProfileEntity;
import com.tis.usecase.energyconsumption.exception.ProfileNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MeterValidator {

    public void validateProfileForMeterEntity(MeterEntity meterEntity) {

    }

    public void validateMeterReadingValues(List<ProfileEntity> profiles, List<MeterEntity> meters) {
        // TODO
    }
}
