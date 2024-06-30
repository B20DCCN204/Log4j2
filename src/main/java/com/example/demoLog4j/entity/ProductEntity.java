package com.example.demoLog4j.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductEntity extends BaseEntity{
    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryEntity category;

    @OneToMany(mappedBy = "product")
    private List<CartItemEntity> cartItems = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<OrderItemEntity> orderItems = new ArrayList<>();
}
