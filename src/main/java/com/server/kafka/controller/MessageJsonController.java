package com.server.kafka.controller;


import com.server.kafka.models.KafkaMessage;
import com.server.kafka.producer.KafkaJsonProducer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka/json/")
public class MessageJsonController {

    private final KafkaJsonProducer kafkaJsonProducer;

    public MessageJsonController(KafkaJsonProducer kafkaJsonProducer) {
        this.kafkaJsonProducer = kafkaJsonProducer;
    }

    @PostMapping("/")
    public ResponseEntity<String> sendMessage(@RequestBody KafkaMessage message) {
        kafkaJsonProducer.sendMessage(message);
        return ResponseEntity.ok("Message json sent successfully");
    }

}




