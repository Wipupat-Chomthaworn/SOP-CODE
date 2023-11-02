package com.example.ordersservice.Query;
//สราง OrderEventsHandler สำหรับรอฟง OrderCreatedEvent เพื่อที่จะเพิ่มขอมูล order ใหมลงใน read database ที่สรางขึ้นมาสำหรับ
//        ให orders service ใชงานแตเพียงผูเดียว โดยใชชื่อตารางวา "orders" และมีคลาส OrderEntity ซึ่งมีโครงสรางดังน

import com.example.ordersservice.Data.OrderEntity;
import com.example.ordersservice.Data.OrderRepository;
import com.example.ordersservice.Event.OrderCreatedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class OrderEventsHandler {
    private final OrderRepository orderRepository;

    public OrderEventsHandler(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }
    @EventHandler
    public void on(OrderCreatedEvent event){
        OrderEntity orderEntity = new OrderEntity();
        BeanUtils.copyProperties(event, orderEntity);
        orderRepository.save(orderEntity);
    }
//    @EventHandler
//    public void on(OrderApprovedEvent event){
//        OrderEntity orderEntity = orderRepository.findByProductId(event.getOrderId());
//        if(orderEntity == null){
//            return;
//        }
//
//        orderEntity.setOrderStatus(event.getOrderStatus());
//        orderRepository.save(orderEntity);
//    }
//
//    @EventHandler
//    public void on(OrderRejectEvent event){
//        OrderEntity orderEntity = orderRepository.findByProductId(event.getOrderId());
//        if(orderEntity == null){
//            return;
//        }
//        orderEntity.setOrderStatus(event.getOrderStatus());
//        orderRepository.save((orderEntity));
//    }

}
