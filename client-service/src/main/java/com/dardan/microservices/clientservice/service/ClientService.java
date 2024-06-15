package com.dardan.microservices.clientservice.service;

import com.dardan.microservices.clientservice.model.response.UserResponse;
import com.dardan.microservices.clientservice.proxy.openfeign.UserServiceFeign;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientService {

        private final UserServiceFeign userServiceFeign;
    public List<UserResponse> getAllUser() {

        return userServiceFeign.getAllUser().getContent();
    }



}
