package com.app.orderservice.module;

import java.util.List;

import com.app.orderservice.domain.OrderRequestDto;
import com.app.orderservice.domain.OrderResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.orderservice.service.OrderService;

import lombok.RequiredArgsConstructor;

@org.springframework.web.bind.annotation.RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    @GetMapping("/orders")
    ResponseEntity<List<OrderResponseDto>> getAllOrders() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/orders/{id}")
    ResponseEntity<OrderResponseDto> findOrderById(@PathVariable String id) {
        OrderResponseDto orderResponseDto = service.findById(id);
        return ResponseEntity.status(200)
                .body(orderResponseDto);
    }

    @DeleteMapping("/orders/{id}")
    ResponseEntity<Void> deleteOrderById(@PathVariable("id") String id) throws JsonProcessingException {
        service.delete(id);
        return ResponseEntity.noContent()
                .build();
    }

    @PostMapping("/orders")
    ResponseEntity<OrderResponseDto> createOrder(@RequestBody OrderRequestDto orderRequestDto) throws JsonProcessingException {
        OrderResponseDto orderResponseDto = service.create(orderRequestDto);
        return ResponseEntity.status(201)
                .body(orderResponseDto);
    }

    @PutMapping("/orders/{id}")
    ResponseEntity<OrderResponseDto> updateOrder(@PathVariable("id") String id,
                                                 @RequestBody OrderRequestDto orderRequestDto) throws JsonProcessingException {
        OrderResponseDto orderResponseDto = service.update(id, orderRequestDto);
        return ResponseEntity.status(200)
                .body(orderResponseDto);
    }

}
