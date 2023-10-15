package com.tis.usecase.energyconsumption.service;

import com.tis.usecase.energyconsumption.domain.MeterEntity;
import com.tis.usecase.energyconsumption.domain.ProfileEntity;
import com.tis.usecase.energyconsumption.repository.MeterRepository;
import com.tis.usecase.energyconsumption.repository.ProfileRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class MeterHandlerService {

    private MeterRepository meterRepository;

    private ProfileRepository profileRepository;

    public void saveAll(List<MeterEntity> meters) {
        List<ProfileEntity> profiles = profileRepository.findAll();
        meters.forEach(meterEntity -> {
            List<ProfileEntity> matchingProfile = profiles.stream().filter(profile -> profile.getName().equals(meterEntity.getProfileName())).collect(Collectors.toList());
            if (matchingProfile.size() != 1) {
                throw new IllegalArgumentException(); // TODO change to a custom exception
            }
            meterEntity.setProfile(matchingProfile.get(0));
        });
        meterRepository.saveAll(meters);
    }
}
