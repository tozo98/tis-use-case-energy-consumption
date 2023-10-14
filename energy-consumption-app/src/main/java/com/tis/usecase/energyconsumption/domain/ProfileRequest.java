package com.tis.usecase.energyconsumption.domain;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Map;

@Data
public class ProfileRequest {
    @NotBlank
    private String name;
    @NotBlank
    private Map<String, Double> fractions;
}
