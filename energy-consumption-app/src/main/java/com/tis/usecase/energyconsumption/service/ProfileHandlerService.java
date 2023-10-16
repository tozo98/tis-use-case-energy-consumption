package com.tis.usecase.energyconsumption.service;

import com.tis.usecase.energyconsumption.domain.ProfileEntity;
import com.tis.usecase.energyconsumption.exception.InvalidProfileException;
import com.tis.usecase.energyconsumption.exception.ProfileNotFoundException;
import com.tis.usecase.energyconsumption.repository.ProfileRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProfileHandlerService {

    private ProfileValidator profileValidator;

    private ProfileRepository profileRepository;

    public void saveAll(List<ProfileEntity> profiles) {
        checkForAlreadyExistingProfiles(profiles);
        profileValidator.validate(profiles);
        profileRepository.saveAll(profiles);
    }

    void checkForAlreadyExistingProfiles(List<ProfileEntity> profiles) {
        profiles.forEach(profileEntity -> {
            if(profileRepository.findByName(profileEntity.getName()).size()  > 0) {
                throw new InvalidProfileException("Profile already exists!");
            }
        });
    }

    public List<ProfileEntity> findAll() {
        return profileRepository.findAll();
    }

    public ProfileEntity findByName(String name) {
        return Optional.ofNullable(profileRepository.findByName(name).get(0))
                .orElseThrow(()-> new ProfileNotFoundException("Profile does not exist!"));
    }
}
