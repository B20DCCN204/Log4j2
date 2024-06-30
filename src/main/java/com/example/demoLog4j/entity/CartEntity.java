package com.example.demoLog4j.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cart")
@Getter
@Setter
public class CartEntity extends BaseEntity{
    @OneToOne(mappedBy = "cart")
    private OrderEntity order;

    @OneToMany(mappedBy = "cart")
    private List<CartItemEntity> cartItems = new ArrayList<>();

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "note")
    private String note;
}
