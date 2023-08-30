package com.krokoq.simple.opentelemetry.rest.domain.db;

import com.krokoq.simple.opentelemetry.rest.domain.db.entity.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemDBRepository extends JpaRepository<ItemEntity, Long> {
}
