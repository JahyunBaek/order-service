package com.example.ordersservice.service;

import com.example.ordersservice.dto.OrdersDto;
import com.example.ordersservice.entity.OrdersEntity;

public interface OrdersService {
    OrdersDto createOrder(OrdersDto ordersDto);
    OrdersDto getOrderByOrderId(String orderId);
    Iterable<OrdersEntity> getOrdersByUserId(String UserId);
}
