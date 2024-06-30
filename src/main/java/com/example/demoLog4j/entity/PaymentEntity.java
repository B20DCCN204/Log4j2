package com.example.demoLog4j.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "payment")
@Getter
@Setter
public class PaymentEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "orderid", nullable = false)
    private OrderEntity order;
    @Column(name = "payment_date")
    private Date paymentDate;
    @Column(name = "payment_method")
    private String paymentMethod;
    @Column(name = "status")
    private String status;
}
