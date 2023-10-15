package com.tis.usecase.energyconsumption.domain;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Map;

@Data
public class ProfileRequest {
    @NotBlank
    private String name;
    @NotNull
    private Map<String, Double> fractions;
}
