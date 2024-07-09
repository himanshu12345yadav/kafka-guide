package com.server.kafka.consumer;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @Value("${kafka.topic.name}")
    private static String topicName;

    @Value("${kakfa.group.name}")
    private static String groupId;

    private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = "${kafka.topic.name}", groupId = "${kafka.group.name}")
    public void consume(String message) {
        logger.info(String.format("Message received , %s", message));
    }


}
