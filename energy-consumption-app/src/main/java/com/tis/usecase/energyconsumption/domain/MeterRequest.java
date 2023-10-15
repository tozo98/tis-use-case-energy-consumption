package com.tis.usecase.energyconsumption.domain;

import lombok.Data;

import java.util.Map;

@Data
public class MeterRequest {
    private String id;
    private String profileName;
    private Map<String, Double> readings;
}
