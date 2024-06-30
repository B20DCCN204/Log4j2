package com.example.demoLog4j.repository;

import com.example.demoLog4j.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Boolean existsByUsername(String Username);
    UserEntity findUserEntityByUsernameAndIsActive(String username, Boolean status);
}
