package com.tis.usecase.energyconsumption.repository;

import com.tis.usecase.energyconsumption.domain.MeterReadingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeterReadingRepository extends JpaRepository<MeterReadingEntity, Long> {
}
