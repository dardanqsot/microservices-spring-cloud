package com.dardan.microservices.clientservice.model.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Setter
@Getter
public class UserServiceResponse {

    private List<UserResponse> content;

}
