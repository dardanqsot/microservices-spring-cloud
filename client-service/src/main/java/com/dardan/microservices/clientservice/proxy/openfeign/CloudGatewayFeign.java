package com.dardan.microservices.clientservice.proxy.openfeign;


import com.dardan.microservices.clientservice.model.request.ProductRequest;
import com.dardan.microservices.clientservice.model.response.ProductResponse;
import com.dardan.microservices.clientservice.model.response.UserServiceResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "cloud-gateway")
public interface CloudGatewayFeign {


    @PostMapping("/api/product-service/saveProduct")
    ProductResponse saveProduct(@RequestBody ProductRequest productDTO);

    @GetMapping("/api/product-service/product")
    List<ProductResponse> getAllProducts();

    @GetMapping("/api/product-service/product/parameter")
    List<ProductResponse> getAllProductsWithParam(@RequestParam("tokens") String flag);

    @GetMapping("/api/product-service/product/{flag}")
    List<ProductResponse> getAllProductsWithFlag(@PathVariable("flag") boolean flag);

    @GetMapping("/api/user-service/mitocode/user")
    UserServiceResponse getAllUser();


}
