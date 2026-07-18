package com.mihir.order_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {


    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public  Order createOrder(@RequestParam  String product, @RequestParam int quantity) throws Exception{
        return orderService.placeOrder(product, quantity);
    }

    @GetMapping("/{id}")
    public Order getOrder(@PathVariable String id) throws Exception {
        Order order = orderService.getOrder(id);
        if (order == null) {
            throw new RuntimeException("Order not found or cache expired");
        }
        return order;
    }

}
