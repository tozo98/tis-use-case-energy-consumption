package com.tis.usecase.energyconsumption.repository;

import com.tis.usecase.energyconsumption.domain.FractionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FractionRepository extends JpaRepository<FractionEntity, Long> {
}
