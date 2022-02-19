package com.app.orderservice.module;

import com.app.orderservice.domain.OrderResponseDto;
import com.app.orderservice.model.OrderStatus;
import org.springframework.test.context.TestPropertySource;

import java.time.Instant;
@TestPropertySource("classpath:application-module.properties")
class HelperControllerTest {

    OrderResponseDto orderResponseDto = new OrderResponseDto();

    public OrderResponseDto getOrderResponseDto() {
        orderResponseDto.setId("6a5d4dc7-82e4-4f64-bbf2-2c801c8d08d0");
        orderResponseDto.setUserId("2");
        orderResponseDto.setStartGeolocation("-82.2,40.2");
        orderResponseDto.setEndGeolocation("-82.2,40.2");
        orderResponseDto.setStatus(OrderStatus.IN_PROCESS);
        orderResponseDto.setStartDelivery(Instant.MIN);
        orderResponseDto.setEndDelivery(Instant.MIN);
        orderResponseDto.setOperatorId(1);
        return orderResponseDto;
    }


}
