package com.tis.usecase.energyconsumption.service;

import com.tis.usecase.energyconsumption.domain.FractionEntity;
import com.tis.usecase.energyconsumption.domain.ProfileEntity;
import com.tis.usecase.energyconsumption.repository.FractionRepository;
import com.tis.usecase.energyconsumption.repository.ProfileRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProfileHandlerService {

    private ProfileValidator profileValidator;

    private ProfileRepository profileRepository;

    private FractionRepository fractionRepository;

    public void saveAll(List<ProfileEntity> profiles) {
        profileValidator.validate(profiles);
        profileRepository.saveAll(profiles);
    }

    public List<ProfileEntity> retrieveAll() {
        return profileRepository.findAll();
    }
}
