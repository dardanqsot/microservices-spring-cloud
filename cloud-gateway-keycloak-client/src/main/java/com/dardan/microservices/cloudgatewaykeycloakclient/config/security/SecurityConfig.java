package com.dardan.microservices.cloudgatewaykeycloakclient.config.security;


import jakarta.ws.rs.HttpMethod;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    public static final String ADMIN = "admin";
    public static final String USER = "user";

    private final JwtAuthConverter jwtAuthConverter;

    @Bean
    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) throws Exception {
        http.authorizeExchange(auth -> {
            auth
                    .pathMatchers(HttpMethod.POST, "/api/product-service/**")
                    .hasRole(ADMIN)
                    .pathMatchers(HttpMethod.GET,
                            "/api/product-service/**")
                    .hasAnyRole(USER, ADMIN)

                    .anyExchange().authenticated();
        });

        http.oauth2ResourceServer(auth -> {
            auth.jwt(jwt -> {
                jwt.jwtAuthenticationConverter(jwtAuthConverter);
            });
        });

        http.csrf(ServerHttpSecurity.CsrfSpec::disable);


        return http.build();
    }


}
