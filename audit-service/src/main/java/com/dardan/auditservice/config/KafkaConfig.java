package com.dardan.auditservice.config;

import com.dardan.auditservice.repository.AuditInfoRepository;
import com.dardan.auditservice.repository.ProductRepository;
import com.dardan.commonmodels.entity.AuditInfo;
import com.dardan.commonmodels.entity.GenericEntity;
import com.dardan.commonmodels.entity.ProductEntity;
import com.dardan.commonmodels.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;


@Slf4j
@Configuration
@RequiredArgsConstructor
public class KafkaConfig {

    private final AuditInfoRepository auditInfoRepository;
    private final ProductRepository productRepository;
    @Value("${kafka.dardan.server:127.0.0.1}")
    private String kafkaServer;
    @Value("${kafka.dardan.port:9092}")
    private String kafkaPort;
    @Value("${kafka.dardan.topic:dardan}")
    private String topicName;

    @Bean
    public ConsumerFactory<String, GenericEntity<? extends GenericEntity>> consumerFactory() {
        Map<String, Object> kafkaProperties = new HashMap<>();
//        var kafkaProperties = new HashMap<>();
        kafkaProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer + ":" + kafkaPort);
        kafkaProperties.put(ConsumerConfig.GROUP_ID_CONFIG, topicName);

        kafkaProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        kafkaProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);

        kafkaProperties.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, JsonDeserializer.class);
        kafkaProperties.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class.getName());

        kafkaProperties.put(JsonDeserializer.KEY_DEFAULT_TYPE, "com.dardan.auditservice.config.KafkaConfig");
        kafkaProperties.put(JsonDeserializer.VALUE_DEFAULT_TYPE, "com.dardan.auditservice.config.KafkaConfig");
        kafkaProperties.put(JsonDeserializer.TRUSTED_PACKAGES, "com.dardan.*");

        return new DefaultKafkaConsumerFactory<>(kafkaProperties);
    }


    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, GenericEntity<? extends GenericEntity>> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, GenericEntity<? extends GenericEntity>> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    @KafkaListener(topics = "mitocode2")
    public void listenTopic(GenericEntity<? extends GenericEntity> obj) {

        if (obj instanceof ProductEntity productEntity) {
            log.info("Almacenando Product Entity");
            productRepository.save(productEntity);
        } else if (obj instanceof UserEntity userEntity) {
            log.info("Almacenando User Entity");
            log.info(userEntity.toString());
        } else if (obj instanceof AuditInfo auditInfo) {

            // Validación o sanitización de datos
            log.info("Almacenando Audit Info");
            auditInfoRepository.save(auditInfo);
        }

    }

}
