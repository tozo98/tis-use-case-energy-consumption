package com.tis.usecase.energyconsumption.repository;

import com.tis.usecase.energyconsumption.domain.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfileRepository extends JpaRepository<ProfileEntity, Long> {

    List<ProfileEntity> findByName(String name);
}
