package com.dardan.microservices.cloudgateway.config.filters;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
//@Component
public class CustomFilter extends AbstractGatewayFilterFactory<CustomFilter.CustomConfiguration> implements Ordered {

    public CustomFilter() {
        super(CustomConfiguration.class);
    }

    @Override
    public String name() {
        return "DardanProductFilter";
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("headerKey", "headerValue");
    }

    @Override
    public GatewayFilter apply(CustomConfiguration config) {

        return ((exchange, chain) -> {

            log.info("============> Custom Filter");

            // Pre filter
            String cookieResponse;
            Optional<String> dardanToken = Optional.ofNullable(exchange.getRequest().getHeaders().getFirst("appCallerName"));
            cookieResponse = dardanToken.orElse("SinCookie");
//            exchange.getRequest().mutate().header("appCallerName", cookieResponse).build();
            exchange.getRequest().mutate().header(config.headerKey, config.headerValue);
            return chain.filter(exchange).then(Mono.fromRunnable(() -> {

                // Post filter
                exchange.getResponse().getCookies().add("TOKEN", ResponseCookie.from("DARDAN_TOKEN", cookieResponse).build());
                log.info(config.headerKey);
                log.info(config.headerValue);
                exchange.getResponse().getHeaders().add(config.headerKey, config.headerValue);
            }));

        });
    }

    @Override
    public int getOrder() {
        return 11;
    }

    @Data
    public static class CustomConfiguration {
        private String headerKey;
        private String headerValue;
    }

}
