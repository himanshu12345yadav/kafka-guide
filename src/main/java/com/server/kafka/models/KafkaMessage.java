package com.server.kafka.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KafkaMessage {

    private int id;
    private String message;
    private int length;

}
