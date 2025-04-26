package com.dardan.microservices.productservice;


import com.dardan.commonmodels.entity.ProductEntity;
import com.dardan.microservices.productservice.service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class ProductServiceApplication implements CommandLineRunner {

    private final ProductRepository productRepository;

    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        productRepository.deleteAll();

        productRepository.save(ProductEntity.builder()
                .productId("P00001")
                .productName("Microservicios Básico")
                .productType("Curso")
                .price(200L)
                .stock(50)
                .build());

        productRepository.save(ProductEntity.builder()
                .productId("P00002")
                .productName("Microservicios Avanzados")
                .productType("Curso")
                .price(300L)
                .stock(40)
                .build());

    }
}
