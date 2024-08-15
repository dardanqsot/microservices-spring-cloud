package com.dardan.microservices.productservice.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http.oauth2Client(Customizer.withDefaults())
                .oauth2Login(oAuth2Login ->
                        oAuth2Login.tokenEndpoint(Customizer.withDefaults())
                                .userInfoEndpoint(Customizer.withDefaults())
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
                .authorizeHttpRequests(auth ->

                        auth.requestMatchers("/product").permitAll()
                                .anyRequest().fullyAuthenticated()
                ).logout(httpLogout -> httpLogout.logoutSuccessUrl("http://localhost:8080")).build();
    }

}
