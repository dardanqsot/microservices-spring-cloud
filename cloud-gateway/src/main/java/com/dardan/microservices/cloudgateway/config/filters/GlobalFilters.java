package com.dardan.microservices.cloudgateway.config.filters;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
public class GlobalFilters implements GlobalFilter {


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        // Pre Filter
//        Optional<String> appCallerName = Optional.ofNullable(exchange.getRequest().getHeaders().getFirst("appCallerName"));
//
//        if (appCallerName.isEmpty()) {
//            exchange.getRequest().mutate().header("appCallerName", "Cloud Gateway").build();
//        }

        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            // PostFilter
            exchange.getResponse().getCookies().add("Token", ResponseCookie.from("DarwinToken", "TOKEN").build());
            exchange.getResponse().getHeaders().add("Darwin-Header", exchange.getRequest().getHeaders().getFirst("appCallerName"));
        }));


    }
}
