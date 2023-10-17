package com.tis.usecase.energyconsumption.service;

import com.tis.usecase.energyconsumption.domain.MeterEntity;
import com.tis.usecase.energyconsumption.domain.MeterReadingEntity;
import com.tis.usecase.energyconsumption.domain.ProfileEntity;
import com.tis.usecase.energyconsumption.exception.MeterAlreadyExistException;
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

    public MeterEntity findById(Long id) {
        MeterEntity meterEntity = meterRepository.findById(id).orElseThrow(() -> new MeterReadingValidationException("Meter does not exist!"));
        meterValidator.validateConsumptionBasedOnFractions(List.of(meterEntity));
        return meterEntity;
    }

    public void saveAll(List<MeterEntity> meters) {
        validateMeterId(meters);
        meters.forEach(this::setProfileForMeterEntity);
        calculateConsumption(meters);
        meterValidator.validateConsumptionBasedOnFractions(meters);
        meterRepository.saveAll(meters);
    }

    void validateMeterId(List<MeterEntity> meters) {
        meters.forEach(meter -> {
            if (meterRepository.existsById(meter.getId())) {
                throw new MeterAlreadyExistException("MeterId already exists!");
            }
        });
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

    void calculateConsumption(List<MeterEntity> meters) {
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

    void sortMeterReadingsBasedOnMonths(List<MeterEntity> meters) {
        meters.forEach(meter -> {
            List<MeterReadingEntity> meterReading = new ArrayList<>(meter.getMeterReadings());
            meterReading.sort(Comparator.comparing(MeterReadingEntity::getMonth));
            meter.setMeterReadings(meterReading);
        });
    }

}
