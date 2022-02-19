package com.app.orderservice.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.Instant;

@Data
@RequiredArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id",nullable = false)
    private String id;

    @Column(name = "user_id")
    private String userId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private OrderStatus status;

    @Column(name = "start_geolocation")
    private String startGeolocation;

    @Column(name = "stop_geolocation")
    private String endGeolocation;

    @Column(name = "start_delivery")
    private Instant startDelivery;

    @Column(name = "end_delivery")
    private Instant endDelivery;

    @Column(name = "operator_id")
    private Integer operatorId;

}
