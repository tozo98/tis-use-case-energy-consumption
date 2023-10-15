package com.tis.usecase.energyconsumption.service;

import com.tis.usecase.energyconsumption.domain.MeterEntity;
import com.tis.usecase.energyconsumption.domain.ProfileEntity;
import com.tis.usecase.energyconsumption.exception.ProfileNotFoundException;
import com.tis.usecase.energyconsumption.repository.MeterRepository;
import com.tis.usecase.energyconsumption.repository.ProfileRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MeterHandlerService {

    private MeterRepository meterRepository;

    private ProfileRepository profileRepository;

    private MeterValidator meterValidator;

    public void saveAll(List<MeterEntity> meters) {
        List<ProfileEntity> profiles = profileRepository.findAll();
        meters.forEach( meterEntity -> {
            ProfileEntity profile = Optional.of(profileRepository.findByName(meterEntity.getProfileName()).get(0)).orElseThrow();
            meterEntity.setProfile(profile);
        });
        meterValidator.validateMeterReadingValues(profiles, meters);
        calculateConsumption(profiles, meters);
        meterRepository.saveAll(meters);
    }

    private void calculateConsumption(List<ProfileEntity> profiles, List<MeterEntity> meters) {
        // TODO
    }

}
