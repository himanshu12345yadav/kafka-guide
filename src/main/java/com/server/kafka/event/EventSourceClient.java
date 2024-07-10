package com.server.kafka.event;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class EventSourceClient {

    @Value("${kafka.sse.url}")
    private String sseUrl;

    @Bean
    public OkHttpClient getHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.MINUTES)
                .writeTimeout(10, TimeUnit.MINUTES)
                .build();
    }

    @Bean
    public Request getRequest() {
        return new Request.Builder()
                .url(sseUrl)
                .header("Accept", "application/json; q=0.5")
                .addHeader("Accept", "text/event-stream")
                .build();
    }


}
