package com.dardan.microservices.productqueryservice.service;


import com.dardan.commonmodels.entity.ProductEntity;
import com.dardan.microservices.productqueryservice.model.dto.ProductDTO;
import com.dardan.microservices.productqueryservice.service.repository.ProductRepository;
import com.dardan.microservices.productqueryservice.util.UtilMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class ProductService {


    private final UtilMapper utilMapper;

    private final ProductRepository productRepository;

    @Value("${server.port}")
    private Integer port;

    public List<ProductDTO> getAllProducts() {
        Iterable<ProductEntity> itProducts = productRepository.findAll();
        return StreamSupport.stream(itProducts.spliterator(), false).map(productEntity -> {
            ProductDTO productDTO = utilMapper.convertEntitytoDTO(productEntity);
            productDTO.setPort(port);
            return productDTO;
        }).collect(Collectors.toList());
    }


}
