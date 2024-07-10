package com.server.kafka.event;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSources;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class KafkaEventSource {

    private final EventListener eventListener;
    private final OkHttpClient client;
    private final Request request;

    @Autowired
    public KafkaEventSource(EventListener eventListener, OkHttpClient client, Request request) {
        this.eventListener = eventListener;
        this.client = client;
        this.request = request;
    }

    @Bean
    public EventSource eventSource() {
        return EventSources.createFactory(this.client).newEventSource(this.request, this.eventListener);
    }
}
