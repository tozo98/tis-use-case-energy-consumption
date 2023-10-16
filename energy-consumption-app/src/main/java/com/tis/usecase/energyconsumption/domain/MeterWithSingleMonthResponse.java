package com.tis.usecase.energyconsumption.domain;

import lombok.Data;

@Data
public class MeterWithSingleMonthResponse {
    private Long id;
    private String profileName;
    private String month;
    private Double consumption;
}
