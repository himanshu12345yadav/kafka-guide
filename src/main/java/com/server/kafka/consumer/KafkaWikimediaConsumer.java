package com.server.kafka.consumer;


import com.server.kafka.models.KafkaMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaWikimediaConsumer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaWikimediaConsumer.class);

    @KafkaListener(topics = "${kafka.topic.wikimedia.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(KafkaMessage message) {
        logger.info(String.format("Kafka message received , %s", message));
    }


}
