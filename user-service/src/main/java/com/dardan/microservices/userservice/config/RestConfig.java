package com.dardan.microservices.userservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.dardan.microservices.userservice.model.entity.UserEntity;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class RestConfig implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
//        RepositoryRestConfigurer.super.configureRepositoryRestConfiguration(config, cors);
        config.setDefaultMediaType(MediaType.APPLICATION_JSON);
        config.useHalAsDefaultJsonMediaType(false);
        config.exposeIdsFor(UserEntity.class);
        config.setBasePath("/dardan");
        config.getExposureConfiguration().forDomainType(UserEntity.class).withItemExposure((metadata, httpMethods) ->
                httpMethods.disable(HttpMethod.DELETE)
        );
    }

    @Override
    public void configureJacksonObjectMapper(ObjectMapper objectMapper) {
        RepositoryRestConfigurer.super.configureJacksonObjectMapper(objectMapper);
    }

}
