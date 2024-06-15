package com.dardan.microservices.clientservice.service;

import com.dardan.microservices.clientservice.model.response.UserResponse;
import com.dardan.microservices.clientservice.model.response.UserResponseRecord;
import com.dardan.microservices.clientservice.proxy.openfeign.UserServiceFeign;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientService {

    private final UserServiceFeign userServiceFeign;
    private final CircuitBreakerFactory circuitBreakerFactory;

    @CircuitBreaker(name = "user-service-cb", fallbackMethod = "alternativeUser")
    public List<UserResponseRecord> getAllUser() {

        //List<UserResponse> records = userServiceFeign.getAllUser().getContent();

        List<UserResponse> records = circuitBreakerFactory.create("dardan")
                .run(() -> userServiceFeign.getAllUser().getContent(),
                        this::alternativeMethod);

        List<UserResponseRecord> recordList = new ArrayList<>();

        for (UserResponse response : records) {
            UserResponseRecord record = new UserResponseRecord(
                    response.getId(),
                    response.getName(),
                    response.getLastname(),
                    response.getUsername(),
                    response.getEmail(),
                    response.getPassword(),
                    response.getRoles()
            );
            recordList.add(record);
        }
        return recordList;
    }

    private List<UserResponse> alternativeMethod(Throwable e) {
        log.info(e.getMessage());
        List<UserResponse> lstUserResponse = new ArrayList<>();
        UserResponse userResponse = UserResponse.builder()
                .id("USER9999")
                .email("usuariofakeœgmail.com")
                .lastname("Fake")
                .name("Usuario")
                .password("MyPassword")
                .roles(new String[]{"FAKE", "ADMIN"})
                .username("userFake")
                .build();
        lstUserResponse.add(userResponse);
        return lstUserResponse;
    }
}
