package com.tis.usecase.energyconsumption.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class MeterReadingEntity {
    @Id
    private Long id;
    private Integer month;
    private Double value;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meter_id")
    private MeterEntity meter;

}
