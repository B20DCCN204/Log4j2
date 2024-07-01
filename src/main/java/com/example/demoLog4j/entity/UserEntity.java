package com.example.demoLog4j.entity;

import com.example.demoLog4j.constants.ConstantSystem;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@Getter
@Setter
public class UserEntity extends BaseEntity{

    @Column(name = "username", unique = true)
    private String username;
    @Column(name = "fullname", nullable = false)
    private String fullname;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "phone", unique = true)
    private String phone;
    @Column(name = "email")
    private String email;
    @Column(name = "is_active", nullable = false)
    private Boolean isActive = ConstantSystem.ACTIVE;
    @ManyToOne
    @JoinColumn(name = "roleid", nullable = false)
    private RoleEntity role;
    @OneToOne(mappedBy = "user")
    private CartEntity cart;
}
