package com.dardan.microservices.productservice.expose.web;


import com.dardan.microservices.productservice.model.dto.ProductDTO;
import com.dardan.microservices.productservice.service.ProductService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping
@RequiredArgsConstructor
@OpenAPIDefinition(
        info = @Info(
                title = "Product Service Microservice",
                version = "0.0.1",
                description = "Módulo del ecosistema de arquitectura de Microservicios para la gestión de productos",
                contact = @Contact(
                        name = "Dardan",
                        url = "http://github.com/dardanqsot",
                        email = "quispesotodaniel@gmail.com"
                ),
                license = @License(
                        name = "Some License",
                        url = "https://github.com/dardanqsot"
                )
        ),
        servers = {
                @Server(url = "http:localhost:9002"),
                @Server(url = "http:localhost:9022")
        },
        tags = @Tag(name = "ProductDardan", description = "Microservicio para la gestión de Productos")
)
public class ProductController {

    private final ProductService productService;


    @Operation(
            description = "Endpoint que guarda productos",
            tags = {"ProductDardan"},
            responses = {
                    @ApiResponse(
                            description = "Response Ok",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ProductDTO.class)
                            )
                    ),
                    @ApiResponse(
                            description = "Bad Request",
                            responseCode = "400",
                            content = @Content(
                                    mediaType = "plain/text",
                                    array = @ArraySchema(
                                            schema = @Schema(implementation = HttpClientErrorException.BadRequest.class)
                                    )
                            )
                    )
            }
    )
    @PostMapping("/saveProduct")
    public ResponseEntity<ProductDTO> saveProduct(@RequestBody ProductDTO productDTO) {
        log.info(productDTO.toString());
        return ResponseEntity.ok(productService.saveProduct(productDTO));
    }

    @Operation(
            description = "Endpoint que lista todos los productos",
            tags = {"ProductDardan"},
            responses = {
                    @ApiResponse(
                            description = "Response Ok",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(
                                            schema = @Schema(implementation = ProductDTO.class)
                                    )
                            )
                    ),
                    @ApiResponse(
                            description = "Internal Server Error",
                            responseCode = "500",
                            content = @Content(
                                    mediaType = "plain/text",
                                    array = @ArraySchema(
                                            schema = @Schema(implementation = NullPointerException.class)
                                    )
                            )
                    )
            }
    )
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
