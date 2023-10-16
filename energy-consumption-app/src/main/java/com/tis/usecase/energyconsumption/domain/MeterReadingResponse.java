package com.tis.usecase.energyconsumption.domain;

import lombok.Data;

@Data
public class MeterReadingResponse {
    private String month;
    private Double reading;
    private Double consumption;
}
