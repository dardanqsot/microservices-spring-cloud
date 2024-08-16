package com.dardan.microservices.cloudgatewaykeycloakclient.config.filters;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.ResponseCookie;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@Slf4j
//@Component
public class CustomFilter extends AbstractGatewayFilterFactory<CustomFilter.CustomConfiguration> {

    public CustomFilter() {
        super(CustomConfiguration.class);
    }

    @Override
    public String name() {
        return "CustomFilterDardan";
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList("headerKey", "headerValue");
    }

    @Override
    public GatewayFilter apply(CustomConfiguration config) {
        return ((exchange, chain) -> {
            // Prefilter
            log.info("[CustomFilter] - [apply]: Pre filter");


            exchange.getRequest().mutate().header("callerName", config.headerValue);

            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                // Post Filter

                exchange.getResponse().getCookies().add("RESPONSE",
                        ResponseCookie.from(config.headerKey, config.headerValue).build());
                exchange.getResponse().getHeaders().add("RESPONSE", config.headerValue);
                log.info("HeaderKey: " + config.headerKey);
                log.info("HeaderValue: " + config.headerValue);
                log.info("[CustomFilter] - [apply]: Post filter");
            }));

        });
    }

    @Data
    public static class CustomConfiguration {
        private String headerKey;
        private String headerValue;
    }

}
