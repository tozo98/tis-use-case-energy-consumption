package com.tis.usecase.energyconsumption.domain;


import lombok.Data;

import java.util.Map;

@Data
public class ProfileResponse {
    private String name;
    private Map<String, Double> fractions;
}
