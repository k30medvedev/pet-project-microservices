package com.app.orderservice.service;

import com.app.orderservice.domain.OrderRequestDto;
import com.app.orderservice.domain.OrderResponseDto;
import com.app.orderservice.repository.OrderRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.app.orderservice.exception.OrderNotFoundException;
import com.app.orderservice.jms.RabbitMqProducer;
import com.app.orderservice.mail.EntityAction;
import com.app.orderservice.model.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@RequiredArgsConstructor
@org.springframework.stereotype.Service
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final RabbitMqProducer rabbitMqProducer;

    public List<OrderResponseDto> findAll() {
        List<Order> orderList = orderRepository.findAll();
        return orderMapper.OrderToOrderDtoList(orderList);
    }

    public OrderResponseDto findById(String id) {
        Order existOrder = orderRepository.findByUUID(id);
        if (existOrder!=null) {
            return orderMapper.orderToResponseDto(existOrder);
        }else throw new OrderNotFoundException(id);
    }

    public OrderResponseDto create(OrderRequestDto orderRequestDto) throws JsonProcessingException {
        Order order = orderMapper.orderDTOtoOrder(orderRequestDto);
        orderRepository.save(order);
        OrderResponseDto response = orderMapper.orderToResponseDto(order);
        rabbitMqProducer.sendEntity(response, EntityAction.CREATE);
        return response;
    }

    public void delete(String orderId) throws JsonProcessingException {
        Order existOrder = orderRepository.findByUUID(orderId);
        if (existOrder != null){
        OrderResponseDto response = orderMapper.orderToResponseDto(existOrder);
        rabbitMqProducer.sendEntity(response,EntityAction.DELETE);
        orderRepository.deleteByUUID(orderId);}
        else throw new OrderNotFoundException(orderId);
    }

    public OrderResponseDto update(String id, OrderRequestDto orderRequestDto) throws JsonProcessingException {
        Order orderExist = orderRepository.findByUUID(id);
        if (orderExist != null) {
            Order order = orderMapper.orderRequestDtoToOrder(orderRequestDto);
            order.setId(id);
            orderRepository.save(order);
            OrderResponseDto response = orderMapper.orderToResponseDto(order);
            rabbitMqProducer.sendEntity(response,EntityAction.UPDATE);
            return response;
        } else return null;
    }

}