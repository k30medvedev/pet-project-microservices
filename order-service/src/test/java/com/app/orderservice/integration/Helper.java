package com.app.orderservice.integration;

import com.app.orderservice.domain.OrderRequestDto;
import com.app.orderservice.model.OrderStatus;

import java.time.Instant;


public class Helper {
    public static OrderRequestDto getRequestDto() {
        OrderRequestDto requestDto = new OrderRequestDto();
        requestDto.setUserId("test");
        requestDto.setStartGeolocation("-82.2,40.2");
        requestDto.setEndGeolocation("-83.2,40.2");
        requestDto.setStatus(OrderStatus.IN_PROCESS);
        requestDto.setStartDelivery(Instant.ofEpochSecond(10000000L));
        requestDto.setEndDelivery(Instant.ofEpochSecond(20000000L));
        requestDto.setOperatorId(1);
        return requestDto;
    }

    public static OrderRequestDto getRequestDto2() {
        OrderRequestDto requestDto = new OrderRequestDto();
        requestDto.setUserId("2");
        requestDto.setStartGeolocation("-92.2,40.2");
        requestDto.setEndGeolocation("-93.2,40.2");
        requestDto.setStatus(OrderStatus.IN_PROCESS);
        requestDto.setStartDelivery(Instant.ofEpochSecond(10000000L));
        requestDto.setEndDelivery(Instant.ofEpochSecond(20000000L));
        requestDto.setOperatorId(2);
        return requestDto;
    }

    public static OrderRequestDto getRequestDto3() {
        OrderRequestDto requestDto = new OrderRequestDto();
        requestDto.setUserId("3");
        requestDto.setStartGeolocation("-82.2,40.2");
        requestDto.setEndGeolocation("-82.2,40.2");
        requestDto.setStatus(OrderStatus.IN_PROCESS);
        requestDto.setStartDelivery(Instant.ofEpochSecond(10000000L));
        requestDto.setEndDelivery(Instant.ofEpochSecond(20000000L));
        requestDto.setOperatorId(3);
        return requestDto;
    }

    }
