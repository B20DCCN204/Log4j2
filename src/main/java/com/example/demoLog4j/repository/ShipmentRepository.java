package com.example.demoLog4j.repository;

import com.example.demoLog4j.entity.ShipmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipmentRepository extends JpaRepository<ShipmentEntity, Long> {
}
