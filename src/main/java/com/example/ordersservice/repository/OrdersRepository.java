package com.example.ordersservice.repository;

import com.example.ordersservice.entity.OrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<OrdersEntity, Long> {
    OrdersEntity findByOrderId(String OrderId);
    Iterable<OrdersEntity> findByUserId(String UserId);
}
