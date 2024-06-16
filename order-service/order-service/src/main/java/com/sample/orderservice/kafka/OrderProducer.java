package com.sample.orderservice.kafka;


import com.sample.basedomain.dto.OrderEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class OrderProducer {

    private static final Logger logger = LoggerFactory.getLogger(OrderProducer.class);

    private NewTopic topic;

    private KafkaTemplate<String, OrderEvent> orderTemplate;

    public OrderProducer(NewTopic topic, KafkaTemplate<String, OrderEvent> orderTemplate) {
        this.topic = topic;
        this.orderTemplate = orderTemplate;
    }

    public void sendOrder(OrderEvent order) {
        logger.info((String.format("Order event -> %s", order.toString())));

        Message<OrderEvent> message = MessageBuilder
                .withPayload(order)
                .setHeader(KafkaHeaders.TOPIC,topic.name())
                .build();

        orderTemplate.send(message);
    }
}
