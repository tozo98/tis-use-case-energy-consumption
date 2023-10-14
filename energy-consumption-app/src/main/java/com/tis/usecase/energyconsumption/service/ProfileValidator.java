package com.tis.usecase.energyconsumption.service;

import com.tis.usecase.energyconsumption.domain.FractionEntity;
import com.tis.usecase.energyconsumption.domain.ProfileEntity;
import com.tis.usecase.energyconsumption.exception.InvalidProfileException;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProfileValidator {

    public void validate(List<ProfileEntity> profiles) {
        profiles.forEach(profile -> {
            double sum = profile.getFractions().stream().mapToDouble(FractionEntity::getValue).sum();
            Set<Month> months = profile.getFractions().stream().map(fraction -> Month.of(fraction.getMonth())).collect(Collectors.toSet());
            if (sum != 1.0 || profile.getFractions().size() != Month.values().length || months.size() != Month.values().length) {
                throw new InvalidProfileException("Profile does not meet the requirement for fractions!");
            }
        });
    }
}
