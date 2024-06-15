package com.dardan.microservices.clientservice.model.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@Builder
@ToString
public class UserResponse {

    private String id;
    private String name;
    private String lastname;
    private String username;
    private String email;
    private String password;
    private String[] roles;

}
