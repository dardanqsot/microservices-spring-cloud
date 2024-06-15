package com.dardan.microservices.clientserviceribbon.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Setter
@Getter
public class UserServiceResponse {

    private List<UserResponse> content;

}
