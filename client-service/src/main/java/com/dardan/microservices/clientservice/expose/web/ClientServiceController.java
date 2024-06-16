package com.dardan.microservices.clientservice.expose.web;

import com.dardan.microservices.clientservice.model.response.ProductResponse;
import com.dardan.microservices.clientservice.model.response.UserResponse;
import com.dardan.microservices.clientservice.model.response.UserResponseRecord;
import com.dardan.microservices.clientservice.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ClientServiceController {

    private final ClientService clientService;

    @GetMapping("/user")
    public List<UserResponseRecord> getAllUser() {
        return clientService.getAllUser();
    }
    @GetMapping("/product")
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        return ResponseEntity.ok(clientService.getAllProducts());
    }

    @GetMapping("/product/{flag}")
    public ResponseEntity<List<ProductResponse>> getAllProducts(@PathVariable("flag") boolean flag) {
        return ResponseEntity.ok(clientService.getAllProducts(flag));
    }
}
