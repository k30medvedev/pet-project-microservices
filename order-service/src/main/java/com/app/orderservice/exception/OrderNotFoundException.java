package com.app.orderservice.exception;

public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(String id) {super("Order not found for id: " + id);}
}
