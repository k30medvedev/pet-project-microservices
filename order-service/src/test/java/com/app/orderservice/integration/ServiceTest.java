package com.app.orderservice.integration;

import com.app.orderservice.domain.OrderResponseDto;
import com.app.orderservice.repository.OrderRepository;
import com.app.orderservice.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

@SpringBootTest
@Testcontainers
public class ServiceTest extends PostgresContainer {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void startSqlContainer() {
        super.startSqlContainer();
    }

    @Test
    void shouldFindAllOrdersTest() throws JsonProcessingException {
        orderService.create(Helper.getRequestDto());
        orderService.create(Helper.getRequestDto2());
        orderService.create(Helper.getRequestDto3());

        List<OrderResponseDto> list = orderService.findAll();
        Assert.assertNotNull(list);
        assert (list.size() == 3);
        orderRepository.deleteAll();
    }

    @Test
    void shouldCreateOrderTest() throws JsonProcessingException {
        orderService.create(Helper.getRequestDto());
        List<OrderResponseDto> list = orderService.findAll();
        assert (list.size() == 1);
        Assert.assertNotNull(list);
        assert (list.get(0).getId().length() == 36); // UUID == 36
        orderRepository.deleteAll();
    }

    @Test
    void shouldDeleteOrderById() throws JsonProcessingException {
        orderService.create(Helper.getRequestDto());
        List<OrderResponseDto> listContainsOrder = orderService.findAll();
        OrderResponseDto orderResponseDto = listContainsOrder.get(0);
        orderService.delete(orderResponseDto.getId());
        List<OrderResponseDto> emptyList = orderService.findAll();
        assert (emptyList.isEmpty());
        orderRepository.deleteAll();
    }


    @Test
    void shouldFindOrderById() throws JsonProcessingException {
        OrderResponseDto actualOrder = orderService.create(Helper.getRequestDto());
        orderService.create(Helper.getRequestDto2());
        orderService.create(Helper.getRequestDto3());

        String id = actualOrder.getId();
        OrderResponseDto expectOrder = orderService.findById(id);

        assert (actualOrder.getId().equals(expectOrder.getId()));
        assert actualOrder.getUserId().equals(expectOrder.getUserId());
        orderRepository.deleteAll();
    }

    @Test
    void shouldUpdateOrderTest() throws JsonProcessingException {
        OrderResponseDto actualOrder = orderService.create(Helper.getRequestDto());
        String id = actualOrder.getId();
        OrderResponseDto updatedOrder = orderService.update(id, Helper.getRequestDto2());
        assert (updatedOrder.getOperatorId().equals(Helper.getRequestDto2().getOperatorId()));
        assert (updatedOrder.getUserId().equals(Helper.getRequestDto2().getUserId()));
        assert (updatedOrder.getStatus()== Helper.getRequestDto2().getStatus());

        orderRepository.deleteAll();
    }

    @Override
    public void stopContainer() {
        super.stopContainer();
    }
}