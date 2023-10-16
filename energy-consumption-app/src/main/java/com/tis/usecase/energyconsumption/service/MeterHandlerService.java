package com.tis.usecase.energyconsumption.service;

import com.tis.usecase.energyconsumption.domain.MeterEntity;
import com.tis.usecase.energyconsumption.domain.MeterReadingEntity;
import com.tis.usecase.energyconsumption.domain.ProfileEntity;
import com.tis.usecase.energyconsumption.exception.MeterReadingValidationException;
import com.tis.usecase.energyconsumption.exception.ProfileNotFoundException;
import com.tis.usecase.energyconsumption.repository.MeterRepository;
import com.tis.usecase.energyconsumption.repository.ProfileRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@AllArgsConstructor
public class MeterHandlerService {

    private MeterRepository meterRepository;

    private ProfileRepository profileRepository;

    private MeterValidator meterValidator;

    public void saveAll(List<MeterEntity> meters) {
        List<ProfileEntity> profiles = profileRepository.findAll();
        meters.forEach(this::setProfileForMeterEntity);
        calculateConsumption(profiles, meters);
        validateConsumptionBasedOnFractions(meters);
        meterRepository.saveAll(meters);
    }

    void validateConsumptionBasedOnFractions(List<MeterEntity> meters) {
        
    }

    void setProfileForMeterEntity(MeterEntity meterEntity) {
        if (meterEntity.getProfile() == null) {
            List<ProfileEntity> profiles = profileRepository.findByName(meterEntity.getProfileName());
            if (profiles.isEmpty()) {
                throw new ProfileNotFoundException("Profile does not exist with profileName: " + meterEntity.getProfileName());
            } else {
                meterEntity.setProfile(profiles.get(0));
            }
        }
    }

    void calculateConsumption(List<ProfileEntity> profiles, List<MeterEntity> meters) {
        sortMeterReadingsBasedOnMonths(meters);
        meters.forEach(meterEntity -> {
            for (int i = 1; i < meterEntity.getMeterReadings().size(); i++) {
                MeterReadingEntity actual = meterEntity.getMeterReadings().get(i);
                MeterReadingEntity previous = meterEntity.getMeterReadings().get(i - 1);
                if (Double.compare(previous.getReading(), actual.getReading()) > 0) {
                    throw new MeterReadingValidationException("Meter reading is invalid");
                }
                if (i == 1) {
                    previous.setConsumption(previous.getReading());
                }
                actual.setConsumption(actual.getReading() - previous.getReading());
            }
        });
    }

    private static void sortMeterReadingsBasedOnMonths(List<MeterEntity> meters) {
        meters.forEach(meter -> {
            List<MeterReadingEntity> meterReading = new ArrayList<>(meter.getMeterReadings());
            meterReading.sort(Comparator.comparing(MeterReadingEntity::getMonth));
            meter.setMeterReadings(meterReading);
        });
    }

}
