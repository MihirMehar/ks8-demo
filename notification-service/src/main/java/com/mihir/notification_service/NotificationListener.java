package com.mihir.notification_service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;


@Service
public class NotificationListener {

    private final ObjectMapper objectMapper = new ObjectMapper();

    // Yeh method automatically call hoga jab bhi Kafka me naya event aayega
    @KafkaListener(topics = "order-events", groupId = "notification-group")
    public void consumeOrderEvent(String orderJson) {
        try {
            // JSON String wapas Order object ban rahi hai
            Order order = objectMapper.readValue(orderJson, Order.class);

            System.out.println("=========================================");
            System.out.println("🔔 [NOTIFICATION] New Order Received!");
            System.out.println("🆔 Order ID: " + order.getId());
            System.out.println("📦 Product: " + order.getProduct() + " (Qty: " + order.getQuantity() + ")");
            System.out.println("✅ Notification sent successfully to user.");
            System.out.println("=========================================");

        } catch (Exception e) {
            System.err.println("❌ Error processing order event: " + e.getMessage());
        }
    }
}