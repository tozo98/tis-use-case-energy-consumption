package com.tis.usecase.energyconsumption.converter;

import com.tis.usecase.energyconsumption.domain.ProfileEntity;
import com.tis.usecase.energyconsumption.domain.ProfileResponse;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.Month;
import java.util.Map;
import java.util.TreeMap;

@Component
public class ProfileResponseConverter implements Converter<ProfileEntity, ProfileResponse> {
    @Override
    public ProfileResponse convert(ProfileEntity profileEntity) {
        ProfileResponse response = new ProfileResponse();
        response.setName(profileEntity.getName());
        Map<String, Double> fractions = new TreeMap<>();
        profileEntity.getFractions().forEach(fraction -> fractions.put(Month.of(fraction.getMonth()).toString().substring(0,3), fraction.getValue()));
        response.setFractions(fractions);
        return response;
    }
}
