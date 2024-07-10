package com.server.kafka.consumer;

import com.server.kafka.models.KafkaMessage;
import com.server.kafka.producer.KafkaJsonProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaJsonConsumer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaJsonProducer.class);

    @KafkaListener(topics = "${kafka.topic.json.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(KafkaMessage message) {
        logger.info(String.format("Kafka json message received , %s", message));
    }

}
