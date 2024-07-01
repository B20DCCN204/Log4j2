package com.example.demoLog4j.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "cart_item")
@Getter
@Setter
public class CartItemEntity extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "cartid", nullable = false)
    private CartEntity cart;

    @ManyToOne
    @JoinColumn(name = "productid", nullable = false)
    private ProductEntity product;

    @Column(name = "quantity")
    private Integer quantity;
}
