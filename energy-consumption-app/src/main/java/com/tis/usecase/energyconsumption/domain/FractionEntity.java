package com.tis.usecase.energyconsumption.domain;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "fraction")
public class FractionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    private ProfileEntity profile;
    private Integer month;
    private Double value;
}
