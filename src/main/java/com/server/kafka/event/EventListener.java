package com.server.kafka.event;

import okhttp3.Response;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class EventListener extends EventSourceListener {

    private final static Logger logger = LoggerFactory.getLogger(EventListener.class);
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${kafka.topic.wikimedia.name}")
    private String topicName;

    public EventListener(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void onEvent(@NotNull EventSource eventSource, @Nullable String id, @Nullable String type, @NotNull String data) {
        super.onEvent(eventSource, id, type, data);
        kafkaTemplate.send(topicName, data);
        logger.info(String.format("ON EVENT: id : %s, type : %s , data : %s", id, type, data));
    }

    @Override
    public void onClosed(@NotNull EventSource eventSource) {
        super.onClosed(eventSource);
        logger.info("ON CLOSED EVENT");
    }

    @Override
    public void onFailure(@NotNull EventSource eventSource, @Nullable Throwable t, @Nullable Response response) {
        super.onFailure(eventSource, t, response);
        if (response != null && response.isSuccessful() && response.body() != null) {
            try {
                logger.info(String.format("ON FAILURE EVENT SUCCESS, %s", response.body().string()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            logger.info(String.format("ON FAILURE EVENT FAIL, %s", response.code()));
        }
    }

    @Override
    public void onOpen(@NotNull EventSource eventSource, @NotNull Response response) {
        super.onOpen(eventSource, response);
        if (response.isSuccessful() && response.body() != null) {
            try {
                logger.info(String.format("ON OPEN EVENT , %s", response.body().string()));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            logger.info(String.format("ON OPEN EVENT , %s", response.code()));
        }
    }
}
