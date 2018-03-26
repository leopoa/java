package com.amarilho.kafkaspringboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class Sender {

    private static final Logger LOGGER = LoggerFactory.getLogger(Sender.class);

    @Value("${kafka.topic.boot}")
    public String topicDefault;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public String send(String payload) {
        LOGGER.info("sending payload='{}' to topic='{}'", payload, topicDefault);
        kafkaTemplate.send(topicDefault, payload);
        return "sent to default topic";
    }

    public String send(String topic, String payload) {
        LOGGER.info("sending payload='{}' to topic='{}'", payload, topic);
        kafkaTemplate.send(topic, payload);
        return "sent customize topic";
    }
}
