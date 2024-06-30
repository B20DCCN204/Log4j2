package com.example.demoLog4j.repository;

import com.example.demoLog4j.entity.OrderEntity;
import com.example.demoLog4j.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
}
