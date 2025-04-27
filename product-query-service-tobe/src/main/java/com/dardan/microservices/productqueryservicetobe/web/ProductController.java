package com.dardan.microservices.productqueryservicetobe.web;


import com.dardan.microservices.productqueryservicetobe.dto.ProductDTO;
import com.dardan.microservices.productqueryservicetobe.service.ProductService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@OpenAPIDefinition(
        info = @Info(
                title = "Product Service Microservice",
                version = "0.0.1",
                description = "Módulo del ecosistema de arquitectura de Microservicios para la gestión de los productos",
                contact = @Contact(
                        name = "Mitocode",
                        url = "https://github.com/mitocode",
                        email = "springcloud@mitocodenetwork.com"
                ),
                license = @License(
                        name = "Some license",
                        url = "https://github.com/jchoy8890"
                )
        ),
        servers = {
                @Server(url = "http://localhost:9001"),
                @Server(url = "http://localhost:9011")
        },
        tags = @Tag(
                name = "ProductService",
                description = "Microservicio para la gestión de productos"
        )

)
public class ProductController {

    private final ProductService productService;

    @Operation(
            description = "Endpoint que lista los productos de la Base de Datos - Sin filtro",
            tags = {"ProductService"},
            responses = {
                    @ApiResponse(
                            description = "Response OK",
                            responseCode = "200",
                            content = @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(
                                            schema = @Schema(
                                                    implementation = ProductDTO.class
                                            )
                                    )
                            )
                    ),
                    @ApiResponse(
                            description = "Response Error",
                            responseCode = "500",
                            content = @Content(
                                    mediaType = "plain/text"
                            )
                    )
            },
            security = @SecurityRequirement(name = "mitocode")
    )
    @GetMapping("/product")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

}
