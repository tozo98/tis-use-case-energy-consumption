package com.tis.usecase.energyconsumption.converter;

import com.tis.usecase.energyconsumption.domain.FractionEntity;
import com.tis.usecase.energyconsumption.domain.ProfileEntity;
import com.tis.usecase.energyconsumption.domain.ProfileRequest;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class ProfileConverter implements Converter<ProfileRequest, ProfileEntity> {

    private MonthConverter monthConverter;

    @Override
    public ProfileEntity convert(ProfileRequest request) {
        ProfileEntity entity = new ProfileEntity();
        entity.setName(request.getName());
        List<FractionEntity> fractions = new ArrayList<>();
        request.getFractions().forEach(
                (key, value) ->
                {
                    FractionEntity fraction = new FractionEntity();
                    fraction.setMonth(monthConverter.convert(key));
                    fraction.setValue(value);
                    fraction.setProfile(entity);
                    fractions.add(fraction);
                });
        entity.setFractions(fractions);
        return entity;
    }
}
