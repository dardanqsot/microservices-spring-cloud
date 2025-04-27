package com.dardan.microservices.productservicecommand.web;


import com.dardan.microservices.productservicecommand.model.dto.ProductDTO;
import com.dardan.microservices.productservicecommand.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping
@RequiredArgsConstructor

public class ProductController {

    private final ProductService productService;


    @PostMapping("/saveProduct")
    public ResponseEntity<ProductDTO> saveProduct(@Valid @RequestBody ProductDTO productDTO) {
        log.info(productDTO.toString());
        return ResponseEntity.ok(productService.saveProduct(productDTO));
    }

}
