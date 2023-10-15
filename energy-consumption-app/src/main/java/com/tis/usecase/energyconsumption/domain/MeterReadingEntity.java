package com.tis.usecase.energyconsumption.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "meter_reading")
public class MeterReadingEntity {
    @Id
    @GeneratedValue(generator = "meter-reading-sequence-generator")
    @GenericGenerator(
            name = "meter-reading-sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "meter_reading_sequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "13"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    private Long id;
    private Integer month;
    private Double reading;
    private Double consumption;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meter_id")
    private MeterEntity meter;

}
