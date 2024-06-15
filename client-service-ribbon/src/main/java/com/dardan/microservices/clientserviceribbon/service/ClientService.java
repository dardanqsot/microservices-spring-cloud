package com.dardan.microservices.clientserviceribbon.service;

import com.dardan.microservices.clientserviceribbon.model.UserResponse;
import com.dardan.microservices.clientserviceribbon.model.UserServiceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final RestTemplate restTemplate;

    public List<UserResponse> getAllUser() {
        UserServiceResponse response =
                restTemplate.getForObject("http://user-service/dardan/user", UserServiceResponse.class);

        return response.getContent();
    }

}
