package com.tis.usecase.energyconsumption.repository;

import com.tis.usecase.energyconsumption.domain.MeterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeterRepository extends JpaRepository<MeterEntity, Long> {
}
