package com.server.kafka.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfiguration {

    @Value("${kafka.topic.default.name}")
    public String topicName;

    @Value("${kafka.topic.json.name}")
    public String topicJsonName;

    @Value("${kafka.topic.wikimedia.name}")
    public String wikimediaTopicName;

    @Bean
    public NewTopic streamTopic() {
        return TopicBuilder.name(topicName).build();
    }

    @Bean
    public NewTopic streamJsonTopic() {
        return TopicBuilder.name(topicJsonName).build();
    }

    @Bean
    public NewTopic wikimediaTopic() {
        return TopicBuilder.name(wikimediaTopicName).build();
    }
}
