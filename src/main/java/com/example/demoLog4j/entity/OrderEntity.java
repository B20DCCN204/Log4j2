package com.example.demoLog4j.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "`order`")
@Getter
@Setter
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customerid", nullable = false)
    private UserEntity user;

    @OneToOne
    @JoinColumn(name = "cartid", nullable = false)
    private CartEntity cart;

    @OneToMany(mappedBy = "order", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<OrderItemEntity> orderItems = new ArrayList<>();

    @OneToOne(mappedBy = "order")
    private PaymentEntity payment;

    @OneToOne(mappedBy = "order")
    private ShipmentEntity shipment;

    @Column(name = "total_money", nullable = false)
    private Double totalMoney;

    @Column(name = "note")
    private String note;

    @Column(name = "order_date", nullable = false)
    private Instant orderDate = Instant.now();

    @Column(name = "status", nullable = false)
    private String status;

}
