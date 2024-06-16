package com.sample.orderservice.controller;


import com.sample.basedomain.dto.Order;
import com.sample.basedomain.dto.OrderEvent;
import com.sample.orderservice.kafka.OrderProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RequestMapping("/api")
@RestController
public class OrderController {

    private OrderProducer orderProducer;

    @Autowired
    public OrderController(OrderProducer orderProducer) {
        this.orderProducer = orderProducer;
    }

    @PostMapping("/orders")
    public String placeOrder(@RequestBody Order order){

        order.setOrderId(UUID.randomUUID().toString());

        OrderEvent orderEvent = new OrderEvent();

        orderEvent.setOrder(order);
        orderEvent.setStatus("PENDING");
        orderEvent.setMessage("Order State is in pending State");
        orderEvent.setOrder(order);

        orderProducer.sendOrder(orderEvent);

        return "Order placed successfully ...";
    }


}
