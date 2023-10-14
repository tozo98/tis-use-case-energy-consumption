package com.tis.usecase.energyconsumption.converter;

import com.tis.usecase.energyconsumption.domain.ProfileEntity;
import com.tis.usecase.energyconsumption.domain.ProfileResponse;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.TreeMap;

@Component
@AllArgsConstructor
public class ProfileResponseConverter implements Converter<ProfileEntity, ProfileResponse> {

    private MonthConverter monthConverter;

    @Override
    public ProfileResponse convert(ProfileEntity profileEntity) {
        ProfileResponse response = new ProfileResponse();
        response.setName(profileEntity.getName());
        Map<String, Double> fractions = new TreeMap<>();
        profileEntity.getFractions().forEach(fraction -> fractions.put(monthConverter.asMonthString(fraction.getMonth()), fraction.getValue()));
        response.setFractions(fractions);
        return response;
    }
}
