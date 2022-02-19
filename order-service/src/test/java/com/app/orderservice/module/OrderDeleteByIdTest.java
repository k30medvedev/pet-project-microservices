package com.app.orderservice.module;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.app.orderservice.jms.RabbitMqProducer;
import com.app.orderservice.model.Order;
import com.app.orderservice.repository.OrderRepository;
import com.app.orderservice.service.OrderMapper;
import com.app.orderservice.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.TestPropertySource;

import static org.mockito.Mockito.*;
@TestPropertySource("classpath:application-module.properties")
class OrderDeleteByIdTest {

    private OrderRepository repository;
    private OrderService service;
    private OrderMapper orderMapper;
    private RabbitMqProducer rabbitMqProducer;

    @BeforeEach
    void setUp() {
        repository = mock(OrderRepository.class);
        orderMapper = mock(OrderMapper.class);
        rabbitMqProducer = mock(RabbitMqProducer.class);
        service = new OrderService(repository, orderMapper, rabbitMqProducer);
    }

    @Test
    void shouldDeleteByIdTest() throws JsonProcessingException {

        // GIVEN
        String id = "6a5d4dc7-82e4-4f64-bbf2-2c801c8d08d0";
        Order order = new Order();
        order.setId(id);

        when(repository.findByUUID(id)).thenReturn(order);
        doNothing().when(repository)
                .deleteByUUID(id);

        // WHEN
        service.delete(id);

        // THEN
        verify(repository, times(1)).findByUUID(id);
        verify(repository, times(1)).deleteByUUID(id);
    }
}