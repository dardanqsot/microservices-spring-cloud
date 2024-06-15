package com.dardan.microservices.clientservice.proxy.openfeign;


import com.dardan.microservices.clientservice.model.response.UserServiceResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "user-service")
public interface UserServiceFeign {

    @GetMapping("/dardan/user")
    UserServiceResponse getAllUser();

}
