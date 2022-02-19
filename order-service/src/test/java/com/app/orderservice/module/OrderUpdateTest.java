package com.app.orderservice.module;

import com.app.orderservice.domain.OrderRequestDto;
import com.app.orderservice.domain.OrderResponseDto;
import com.app.orderservice.jms.RabbitMqProducer;
import com.app.orderservice.model.Order;
import com.app.orderservice.model.OrderStatus;
import com.app.orderservice.repository.OrderRepository;
import com.app.orderservice.service.OrderMapper;
import com.app.orderservice.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
@TestPropertySource("classpath:application-module.properties")
class OrderUpdateTest {

    @MockBean
    private OrderRepository repository;
    @MockBean
    private OrderMapper orderMapper;

    private OrderService service;
    private RabbitMqProducer rabbitMqProducer;

    @BeforeEach
    void setUp() {
        repository = mock(OrderRepository.class);
        orderMapper = mock(OrderMapper.class);
        rabbitMqProducer = mock(RabbitMqProducer.class);
        service = new OrderService(repository, orderMapper, rabbitMqProducer);
    }

    @Test
    void shouldUpdateOrderTest() throws JsonProcessingException {

        // GIVEN
        String id = "6a5d4dc7-82e4-4f64-bbf2-2c801c8d08d0";
        Order order = mock(Order.class);
        OrderRequestDto requestDto = mock(OrderRequestDto.class);
        OrderResponseDto responseDto = mock(OrderResponseDto.class);
        when(requestDto.getUserId()).thenReturn("6a5d4dc7-82e4-4f64-bbf2-2c801c8d08d0");
        when(requestDto.getStartGeolocation()).thenReturn("-82.2,40.2");
        when(requestDto.getEndGeolocation()).thenReturn("-52.2,40.2");
        when(requestDto.getStatus()).thenReturn(OrderStatus.IN_PROCESS);
        when(requestDto.getStartDelivery()).thenReturn(Instant.ofEpochSecond(2001 - 1 - 1));
        when(requestDto.getEndDelivery()).thenReturn(Instant.ofEpochSecond(2001 - 1 - 1));
        when(requestDto.getOperatorId()).thenReturn(3);

        // WHEN
        when(repository.findByUUID(id)).thenReturn(order);
        when(orderMapper.orderRequestDtoToOrder(requestDto)).thenReturn(order);

        assertNotNull(order);
        when(repository.save(order)).thenReturn(order);
        when(orderMapper.orderToResponseDto(order)).thenReturn(responseDto);
        order.setId(id);

        // THEN
        Assertions.assertEquals(service.update(id, requestDto), responseDto);
        assertNotNull(requestDto);

        verify(repository, times(1)).save(order);
    }
}