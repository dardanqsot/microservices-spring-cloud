package com.dardan.microservices.clientservice.proxy.openfeign;

import com.dardan.microservices.clientservice.model.request.ProductRequest;
import com.dardan.microservices.clientservice.model.response.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "product-service")
public interface ProductServiceFeign {


    @PostMapping("/saveProduct")
    ProductResponse saveProduct(@RequestBody ProductRequest productDTO);

    @GetMapping("/product")
    List<ProductResponse> getAllProducts();

    @GetMapping("/product/parameter")
    List<ProductResponse> getAllProductsWithParam(@RequestParam("tokens") String flag);

    @GetMapping("/product/{flag}")
    List<ProductResponse> getAllProductsWithFlag(@PathVariable("flag") boolean flag);

}
