package com.dardan.microservices.clientservice.service;

import com.dardan.microservices.clientservice.model.response.ProductResponse;
import com.dardan.microservices.clientservice.model.response.UserResponse;
import com.dardan.microservices.clientservice.model.response.UserResponseRecord;
import com.dardan.microservices.clientservice.proxy.openfeign.ProductServiceFeign;
import com.dardan.microservices.clientservice.proxy.openfeign.UserServiceFeign;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
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
    private final ProductServiceFeign productServiceFeign;
    //private final CircuitBreakerFactory circuitBreakerFactory;

    @CircuitBreaker(name = "user-service-cb", fallbackMethod = "alternativeMethod")
    public List<UserResponseRecord> getAllUser() {
        System.out.println("EN RECORDS!");
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

    /*
        @CircuitBreaker(name = "user-service-cb", fallbackMethod = "alternativeMethod")
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

    */
    @CircuitBreaker(name = "product-service-cb", fallbackMethod = "alternativeProduct")
    public List<ProductResponse> getAllProducts() {
        return productServiceFeign.getAllProducts();
    }

    @CircuitBreaker(name = "product-service-cb", fallbackMethod = "alternativeProduct")
    public List<ProductResponse> getAllProducts(boolean flag) {
        return productServiceFeign.getAllProductsWithFlag(flag);
    }


    private List<ProductResponse> alternativeProduct(Throwable e) {
        log.info(e.getMessage());
        List<ProductResponse> lstUserResponse = new ArrayList<>();
        ProductResponse userResponse = ProductResponse.builder()
                .productId("P999999")
                .productName("Producto Fake")
                .productType("Fake")
                .price(20L)
                .stock(100)
                .build();
        lstUserResponse.add(userResponse);
        return lstUserResponse;
    }

    private List<UserResponseRecord> alternativeMethod(Throwable e) {
        //log.info(e.getMessage());
        System.out.println("EN ALTERNATIVE");
        List<UserResponseRecord> lisRecor = new ArrayList<>();
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
        return lisRecor;
    }


}
