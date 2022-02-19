package com.app.orderservice.module;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.app.orderservice.jms.RabbitMqProducer;
import com.app.orderservice.service.OrderMapper;
import com.app.orderservice.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.app.orderservice.domain.OrderRequestDto;
import com.app.orderservice.domain.OrderResponseDto;
import com.app.orderservice.model.Order;
import com.app.orderservice.repository.OrderRepository;
import org.springframework.test.context.TestPropertySource;

@TestPropertySource("classpath:application-module.properties")
class OrderCreateTest {

    private OrderRepository repository;
    private OrderMapper orderMapper;
    private HelperServiceTest helper;
    private RabbitMqProducer rabbitMqProducer;
    private OrderService service;

    @BeforeEach
    void setUp() {
        repository = mock(OrderRepository.class);
        orderMapper = mock(OrderMapper.class);
        rabbitMqProducer = mock(RabbitMqProducer.class);
        helper = new HelperServiceTest();
        service = new OrderService(repository, orderMapper,rabbitMqProducer);
    }

    @Test
    void shouldCreateOrderTest() throws JsonProcessingException {

        // GIVEN
        Order order = new Order();
        OrderResponseDto responseDto = new OrderResponseDto();
        OrderRequestDto requestDto = helper.getRequestDto();

        when(orderMapper.orderDTOtoOrder(requestDto)).thenReturn(order);
        when(repository.save(order)).thenReturn(order);
        when(orderMapper.orderToResponseDto(order)).thenReturn(responseDto);

        // WHEN
        assertEquals(service.create(requestDto), responseDto);
        assertNotNull(requestDto);

        // THEN
        verify(repository, times(1)).save(order);
        verifyNoMoreInteractions(repository);

    }
}