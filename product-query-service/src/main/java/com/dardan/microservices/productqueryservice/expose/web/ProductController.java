package com.dardan.microservices.productqueryservice.expose.web;


import com.dardan.microservices.productqueryservice.model.dto.ProductDTO;
import com.dardan.microservices.productqueryservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping
@RequiredArgsConstructor

public class ProductController {

    private final ProductService productService;

    @GetMapping("/product")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

}
