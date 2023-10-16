package com.tis.usecase.energyconsumption.domain;

import lombok.Data;

import java.util.List;

@Data
public class MeterResponse {
    private Long id;
    private String profileName;
    private Double totalConsumption;
    private List<MeterReadingResponse> meterReadings;


}
