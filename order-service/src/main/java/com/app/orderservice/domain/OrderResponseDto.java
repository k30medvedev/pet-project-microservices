package com.app.orderservice.domain;

import com.app.orderservice.model.OrderStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
public class OrderResponseDto {

    private String id;

    private String userId;

    private OrderStatus status;

    private String startGeolocation;

    private String endGeolocation;

    private Instant startDelivery;

    private Instant endDelivery;

    private Integer operatorId;

}
