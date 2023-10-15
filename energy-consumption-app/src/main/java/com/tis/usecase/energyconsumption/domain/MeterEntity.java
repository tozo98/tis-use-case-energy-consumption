package com.tis.usecase.energyconsumption.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class MeterEntity {

    @Id
    private Long id;
    @Transient
    private String profileName;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    private ProfileEntity profile;
    @OneToMany(mappedBy = "meter", cascade = CascadeType.ALL)
    private List<MeterReadingEntity> meterReadings;
}
