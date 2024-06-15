package com.dardan.microservices.clientservice.model.response;

import java.util.Objects;

public record UserResponseRecord(
        String id,
        String name,
        String last,
        String username,
        String email,
        String password,
        String[] roles) {

    public UserResponseRecord {
        Objects.nonNull(name);
        Objects.nonNull(last);
    }


}
