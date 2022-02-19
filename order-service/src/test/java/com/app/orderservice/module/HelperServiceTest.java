package com.app.orderservice.module;

import static org.mockito.Mockito.mock;

import java.time.Instant;

import com.app.orderservice.domain.OrderRequestDto;
import com.app.orderservice.domain.OrderResponseDto;
import com.app.orderservice.model.OrderStatus;

class HelperServiceTest {
    OrderRequestDto requestDto = new OrderRequestDto();
    OrderResponseDto responseDto = new OrderResponseDto();

    public OrderRequestDto getRequestDto() {
        requestDto.setUserId("6a5d4dc7-82e4-4f64-bbf2-2c801c8d08d0");
        requestDto.setStartGeolocation("-82.2,40.2");
        requestDto.setEndGeolocation("-82.2,40.2");
        requestDto.setStatus(OrderStatus.IN_PROCESS);
        requestDto.setStartDelivery(Instant.ofEpochSecond(2001 - 1 - 1));
        requestDto.setEndDelivery(Instant.ofEpochSecond(2001 - 1 - 1));
        requestDto.setOperatorId(3);
        return requestDto;
    }

    public OrderResponseDto getResponseDto() {
        responseDto.setId("6a5d4dc7-82e4-4f64-bbf2-2c801c8d08d0");
        responseDto.setUserId("2");
        responseDto.setStartGeolocation("-82.2,40.2");
        responseDto.setEndGeolocation("-82.2,40.2");
        responseDto.setStatus(OrderStatus.IN_PROCESS);
        responseDto.setStartDelivery(Instant.ofEpochSecond(2001 - 1 - 1));
        responseDto.setEndDelivery(Instant.ofEpochSecond(2001 - 1 - 1));
        responseDto.setOperatorId(3);
        return responseDto;
    }

}
