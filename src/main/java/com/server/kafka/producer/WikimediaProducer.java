package com.server.kafka.producer;


import com.server.kafka.event.KafkaEventSource;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Service
public class WikimediaProducer {

    private static final Logger logger = LoggerFactory.getLogger(WikimediaProducer.class);

    private final KafkaEventSource kafkaEventSource;
    private final OkHttpClient client;

    @Autowired
    public WikimediaProducer(KafkaEventSource kafkaEventSource, OkHttpClient client, KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaEventSource = kafkaEventSource;
        this.client = client;
    }

    public void startStreaming() throws InterruptedException {
        // Read event source data and send to the kafka topic

        logger.info("Starting SSE streaming");

        client.newCall(this.kafkaEventSource.eventSource().request()).enqueue(new Callback() {

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) {
                logger.info(String.format("Message received, %s", response.body()));
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                logger.error(String.format("Error caught while reading , %s", e.getLocalizedMessage()));
            }
        });


        TimeUnit.MINUTES.sleep(10);
    }

}
