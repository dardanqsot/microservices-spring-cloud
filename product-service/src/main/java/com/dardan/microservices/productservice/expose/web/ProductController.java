package com.dardan.microservices.productservice.expose.web;


import com.dardan.microservices.productservice.model.dto.ProductDTO;
import com.dardan.microservices.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;


    @PostMapping("/saveProduct")
    public ResponseEntity<ProductDTO> saveProduct(@RequestBody ProductDTO productDTO) {
        log.info(productDTO.toString());
        return ResponseEntity.ok(productService.saveProduct(productDTO));
    }

    @GetMapping("/product")
    public ResponseEntity<List<ProductDTO>> getAllProducts() throws InterruptedException {
        log.info("Probando Circuit Breaker con Timeout");
        TimeUnit.MILLISECONDS.sleep(1001L);
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/product/parameter")
    public ResponseEntity<List<ProductDTO>> getAllProductsWithParam(@RequestParam("tokens") String flag) {
        log.info(flag);
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/product/{flag}")
    public ResponseEntity<List<ProductDTO>> getAllProductsWithFlag(@PathVariable("flag") boolean flag
            , @RequestHeader("appCallerName") String appCallerName

    ) throws Exception {

        log.info("App Caller Name: " + appCallerName);

        if (flag) {
            throw new Exception("Probando Circuit Breaker");
        }
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> getAllProductsWithParameter(@RequestParam("flag") boolean flag
            , @RequestHeader("appCallerName") String appCallerName

    ) throws Exception {

        log.info("App Caller Name: " + appCallerName);

        if (flag) {
            throw new Exception("Probando Circuit Breaker");
        }
        return ResponseEntity.ok(productService.getAllProducts());
    }

}
