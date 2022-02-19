package com.app.orderservice.module;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

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
class OrderFindAllTest {

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
    void shouldFindAllTest() {

        // GIVEN
        List<Order> orderList = new ArrayList<>();
        List<OrderResponseDto> listDto = new ArrayList<>();

        when(repository.findAll()).thenReturn(orderList);
        when(orderMapper.OrderToOrderDtoList(orderList)).thenReturn(listDto);

        // WHEN
        List<OrderResponseDto> listExpected = service.findAll();
        List<OrderResponseDto> listActual = orderMapper.OrderToOrderDtoList(repository.findAll());

        // THEN
        verify(repository, times(2)).findAll();
        verifyNoMoreInteractions(repository);

        assertEquals(listExpected, listActual);

    }
}