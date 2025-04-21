package com.dardan.microservices.productservice.service;

import com.dardan.common.stub.model.UserDTO;
import com.dardan.microservices.productservice.model.dto.ProductDTO;
import com.dardan.microservices.productservice.model.entity.ProductEntity;
import com.dardan.microservices.productservice.service.repository.ProductRepository;
import com.dardan.microservices.productservice.util.UtilMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
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


    public ProductDTO saveProduct(ProductDTO productDTO) {
        ProductEntity productEntity = utilMapper.convertDTOtoEntity(productDTO);
        productRepository.save(productEntity);
        productDTO.setPort(port);
        return productDTO;
    }


    public List<ProductDTO> getAllProducts() {
        UserDTO userDTO = new UserDTO();
        Iterable<ProductEntity> itProducts = productRepository.findAll();
        return StreamSupport.stream(itProducts.spliterator(), false).map(productEntity -> {
            ProductDTO productDTO = ProductDTO.builder().build();
            BeanUtils.copyProperties(productEntity, productDTO);
            productDTO.setPort(port);
            return productDTO;
        }).collect(Collectors.toList());
    }

}
