package com.example.ordersservice.Event;

import com.example.ordersservice.Data.OrderEntity;
import com.example.ordersservice.OrderStatus;
import lombok.Data;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;

@Data
public class OrderCreatedEvent {

    private String orderId;
    private String productId;
    private String userId;
    private int quantity;
    private String addressId;
    private OrderStatus orderStatus;


}
