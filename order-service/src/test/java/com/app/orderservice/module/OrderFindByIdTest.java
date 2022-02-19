package com.app.orderservice.module;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.app.orderservice.jms.RabbitMqProducer;
import com.app.orderservice.service.OrderMapper;
import com.app.orderservice.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.app.orderservice.domain.OrderResponseDto;
import com.app.orderservice.model.Order;
import com.app.orderservice.repository.OrderRepository;
import org.springframework.test.context.TestPropertySource;

@TestPropertySource("classpath:application-module.properties")
class OrderFindByIdTest {

    private OrderRepository repository;
    private OrderService service;
    private OrderMapper orderMapper;
    private RabbitMqProducer rabbitMqProducer;

    @BeforeEach
    void setUp() {
        repository = mock(OrderRepository.class);
        orderMapper = mock(OrderMapper.class);
        rabbitMqProducer = mock(RabbitMqProducer.class);
        service = new OrderService(repository, orderMapper,rabbitMqProducer);
    }

    @Test
    void shouldFindByIdTest() {

        // GIVEN
        String id = "6a5d4dc7-82e4-4f64-bbf2-2c801c8d08d0";
        Order actualOrder = mock(Order.class);
        OrderResponseDto orderResponseDto = new OrderResponseDto();

        when(repository.findByUUID(id)).thenReturn(actualOrder);
        when(orderMapper.orderToResponseDto(actualOrder)).thenReturn(orderResponseDto);

        // WHEN
        OrderResponseDto expectedOrder = service.findById(id);
        OrderResponseDto actual = orderMapper.orderToResponseDto(repository.findByUUID(id));

        // THEN
        verify(repository, times(2)).findByUUID(id);
        verifyNoMoreInteractions(repository);

        assertEquals(expectedOrder, actual);
    }
}