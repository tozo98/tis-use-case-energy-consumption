package com.tis.usecase.energyconsumption.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "meter")
public class MeterEntity {

    @Id
//    @GeneratedValue(generator = "meter-sequence-generator")
//    @GenericGenerator(
//            name = "meter-sequence-generator",
//            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
//            parameters = {
//                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "meter_sequence"),
//                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "2"),
//                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
//            }
//    )
    private Long id;
    @Transient
    private String profileName;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    private ProfileEntity profile;
    @OneToMany(mappedBy = "meter", cascade = CascadeType.ALL)
    private List<MeterReadingEntity> meterReadings;
}
