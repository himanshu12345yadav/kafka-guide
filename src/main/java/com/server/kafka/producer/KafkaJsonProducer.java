package com.server.kafka.producer;


import com.server.kafka.models.KafkaMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;


@Service
public class KafkaJsonProducer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaJsonProducer.class);

    @Value("${kafka.topic.json.name}")
    private String topicName;

    private final KafkaTemplate<String, KafkaMessage> kafkaTemplate;

    public KafkaJsonProducer(KafkaTemplate<String, KafkaMessage> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(KafkaMessage message) {
        logger.info(String.format("Message sent to kafka , %s", message.toString()));
        Message<KafkaMessage> kafkaMessage = MessageBuilder.withPayload(message).setHeader(KafkaHeaders.TOPIC, topicName).build();
        kafkaTemplate.send(kafkaMessage);
    }

}

