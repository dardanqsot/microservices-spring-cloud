package com.dardan.microservices.productservice.config;

import jakarta.ws.rs.core.GenericEntity;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    @Value("${kafka.dardan.server:127.0.0.1}")
    private String kafkaServer;

    @Value("${kafka.dardan.port:9092}")
    private String kafkaPort;

    public ProducerFactory<String, Object> producerFactory() {
        Map<String, Object> kafkaProperties = new HashMap<>();
//        var kafkaProperties = new HashMap<>();
        kafkaProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer + ":" + kafkaPort);
        kafkaProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        kafkaProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(kafkaProperties);
    }

    @Bean
    public KafkaTemplate<String, Object> kafkaTemplatHole() {
        return new KafkaTemplate<>(producerFactory());
    }

}
