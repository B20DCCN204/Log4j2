package com.example.demoLog4j.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "shipment")
@Getter
@Setter
public class ShipmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "orderid", nullable = false)
    private OrderEntity order;
    @Column(name = "shipment_date")
    private Date shipmentDate;
    @Column(name = "shipment_address")
    private String shipmentAddress;
    @Column(name = "status")
    private String status;
}
