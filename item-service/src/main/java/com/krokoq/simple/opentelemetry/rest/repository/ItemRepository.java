package com.krokoq.simple.opentelemetry.rest.repository;

import com.krokoq.simple.opentelemetry.rest.domain.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<ItemEntity, Long> {
}
