package com.dardan.microservices.clientservice.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class CustomCircuitBreakerConfig {

    @Bean
    public Customizer<Resilience4JCircuitBreakerFactory> customDefaultConfig() {

        return factory -> factory.configureDefault(
                id -> new Resilience4JConfigBuilder(id)
                .circuitBreakerConfig(CircuitBreakerConfig.custom()
                        .slidingWindowSize(15) // Default 100
                        .failureRateThreshold(30) // Default 50
                        .waitDurationInOpenState(Duration.ofSeconds(20L)) // Default 60 seg
                        .permittedNumberOfCallsInHalfOpenState(5) // Default 10

                        // Métricas para llamadas lentas
                        .slowCallRateThreshold(50) // Default 50
                        .slowCallDurationThreshold(Duration.ofMillis(1500L)) // Default 1 seg

                        .build())
              //  .timeLimiterConfig(TimeLimiterConfig.custom()
              //          .timeoutDuration(Duration.ofSeconds(2L))
              //          .build())
                .build()
        );
    }

}
