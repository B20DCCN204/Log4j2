package com.example.demoLog4j.repository;

import com.example.demoLog4j.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    RoleEntity findRoleEntityByCode(String code);
}
