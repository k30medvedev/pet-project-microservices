package com.app.orderservice.domain;

import java.time.Instant;

import com.app.orderservice.model.OrderStatus;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OrderRequestDto {

    private String userId;

    private OrderStatus status;

    private String startGeolocation;

    private String endGeolocation;

    private Instant startDelivery;

    private Instant endDelivery;

    private Integer operatorId;

}
