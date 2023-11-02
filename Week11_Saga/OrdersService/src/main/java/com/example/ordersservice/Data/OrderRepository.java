package com.example.ordersservice.Data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, String> {
    OrderEntity findByOrderId(String orderId);

//    OrderEntity findByOrderIdOrTitle(String orderId, String title);

}