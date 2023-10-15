package com.tis.usecase.energyconsumption.domain;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Map;

@Data
public class MeterRequest {
    @NotNull
    private Long meterId;
    @NotBlank
    private String profileName;
    @NotNull
    private Map<String, Double> readings;
}
