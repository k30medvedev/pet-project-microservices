package com.app.orderservice.service;

import com.app.orderservice.domain.OrderRequestDto;
import com.app.orderservice.domain.OrderResponseDto;
import com.app.orderservice.model.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.List;

@RequiredArgsConstructor
@Component
@Slf4j
public class OrderMapper {

    private final ModelMapper modelMapper;

    public List<OrderResponseDto> OrderToOrderDtoList(List<Order> orderList) {
        Type listType = new TypeToken<List<OrderResponseDto>>() {
        }.getType();
        return modelMapper.map(orderList, listType);
    }

    public Order orderDTOtoOrder(OrderRequestDto orderRequestDto) {
        return modelMapper.map(orderRequestDto, Order.class);
    }

    public OrderResponseDto orderToResponseDto(Order order) {
        return modelMapper.map(order, OrderResponseDto.class);
    }

    public Order orderRequestDtoToOrder(OrderRequestDto requestDto) {
        return modelMapper.map(requestDto, Order.class);
    }
}
