package com.tis.usecase.energyconsumption.domain;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "fraction")
public class FractionEntity {
    @Id
    @GeneratedValue(generator = "fraction-sequence-generator")
    @GenericGenerator(
            name = "fraction-sequence-generator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "fraction_sequence"),
                    @org.hibernate.annotations.Parameter(name = "initial_value", value = "13"),
                    @org.hibernate.annotations.Parameter(name = "increment_size", value = "1")
            }
    )
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    private ProfileEntity profile;
    private Integer month;
    private Double value;
}
