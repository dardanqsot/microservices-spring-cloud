package com.dardan.microservices.clientservice.service;

import com.dardan.microservices.clientservice.model.response.UserResponse;
import com.dardan.microservices.clientservice.model.response.UserResponseRecord;
import com.dardan.microservices.clientservice.proxy.openfeign.UserServiceFeign;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientService {

        private final UserServiceFeign userServiceFeign;
    public List<UserResponseRecord> getAllUser() {
        List<UserResponse> records = userServiceFeign.getAllUser().getContent();

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
}
