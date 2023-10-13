package com.tis.usecase.energyconsumption.domain;

import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "fraction")
public class FractionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(targetEntity = ProfileEntity.class)
    private Long profile;
    private Integer month;
    private Double value;
}
