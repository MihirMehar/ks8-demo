package com.mihir.order_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class OrderService {


    @Autowired
    private KafkaTemplate<String ,String> kafkaTemplate;

    @Autowired
    private RedisTemplate <String,String> redisTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public Order placeOrder(String product, int quantity) throws Exception{
        String orderId = UUID.randomUUID().toString().substring(0, 8);
        Order order = new Order(orderId,product,quantity);

        // step 1Redis me cache karo (60 second ke liye)
        String orderScan = objectMapper.writeValueAsString(order);
        redisTemplate.opsForValue().set("order:" +orderId, orderScan, 60, TimeUnit.SECONDS);

        // Step 2 kafka pe event bhejo
        // Pehla argument humesha TOPIC NAME hona chahiye jo ki String literal "order-events" hai
        kafkaTemplate.send("order-events", orderScan);
         return  order;
    }

    public Order getOrder(String orderId) throws Exception{
        String cached = redisTemplate.opsForValue().get("order:" +orderId);
        if (cached == null) {
            return null;
        }
        return objectMapper.readValue(cached, Order.class);
    }
}
