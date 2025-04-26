package com.dardan.microservices.productservice.util;

import jakarta.ws.rs.core.GenericEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaUtil {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${kafka.darwin.topic:mitocode2}")
    private String topicName;

    public void sendMessage(Object obj) {
        kafkaTemplate.send(topicName, obj);
    }

}
