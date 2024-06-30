package com.example.demoLog4j.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "order_item")
@Getter
@Setter
public class OrderItemEntity extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "orderid", nullable = false)
    private OrderEntity order;

    @ManyToOne
    @JoinColumn(name = "productid", nullable = false)
    private ProductEntity product;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "price")
    private Double price;
}
