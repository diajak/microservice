package com.sample.stockservice.kafka;

import com.sample.basedomain.dto.OrderEvent;
import org.slf4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(OrderConsumer.class);

    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(OrderEvent event) {
        logger.info(String.format("#### -> Order event received in stock Service -> %s", event.toString()));

    }




}
