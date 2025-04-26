package com.dardan.microservices.productservice.service;

import com.dardan.commonmodels.entity.AuditInfo;
import com.dardan.commonmodels.entity.UserEntity;
import com.dardan.commonmodels.entity.ProductEntity;
import com.dardan.microservices.productservice.model.dto.ProductDTO;
import com.dardan.microservices.productservice.service.repository.ProductRepository;
import com.dardan.microservices.productservice.util.KafkaUtil;
import com.dardan.microservices.productservice.util.UtilMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final UtilMapper utilMapper;
    private final ProductRepository productRepository;
    private final KafkaUtil kafkaUtil;

    @Value("${server.port}")
    private Integer port;


    public ProductDTO saveProduct(ProductDTO productDTO) {
        ProductEntity productEntity = utilMapper.convertDTOtoEntity(productDTO);
        productRepository.save(productEntity);
        productDTO.setPort(port);
        return productDTO;
    }


    public List<ProductDTO> getAllProducts() {

        Iterable<ProductEntity> itProducts = productRepository.findAll();

        kafkaUtil.sendMessage(UserEntity.builder()
                .name("Darwin")
                .lastname("Quispe")
                .username("Soto")
                .email("quispesotodaniel@gmail.com")
                .build());

        kafkaUtil.sendMessage(AuditInfo.builder()
                .appCallerName("Product Service")
                .currentTimestamp(System.currentTimeMillis())
                .message("Prueba desde Product Service")
                .opnNumber(String.valueOf(new Random().nextInt()))
                .statusCode("200")
                .exception(null)
                .exceptionDetails(null)
                .build());

        return StreamSupport.stream(itProducts.spliterator(), false).map(productEntity -> {
            ProductDTO productDTO = ProductDTO.builder().build();
            BeanUtils.copyProperties(productEntity, productDTO);
            productDTO.setPort(port);

            kafkaUtil.sendMessage(productEntity);

            return productDTO;
        }).collect(Collectors.toList());
    }

    public String updateProduct(String productId, Integer quantity) {

        ProductEntity productEntity = productRepository.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado"));

        productEntity.setStock(productEntity.getStock() - quantity);
        productRepository.save(productEntity);
        return "OK";

    }

}
