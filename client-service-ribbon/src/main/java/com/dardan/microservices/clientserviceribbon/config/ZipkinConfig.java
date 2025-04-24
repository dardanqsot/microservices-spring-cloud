package com.dardan.microservices.clientserviceribbon.config;

import brave.sampler.Sampler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ZipkinConfig {

    @Bean
    public Sampler alwaysSampler(){
        return Sampler.ALWAYS_SAMPLE;
    }

}
