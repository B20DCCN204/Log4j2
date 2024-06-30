package com.example.demoLog4j.repository;

import com.example.demoLog4j.entity.CartEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<CartEntity, Long> {
}
