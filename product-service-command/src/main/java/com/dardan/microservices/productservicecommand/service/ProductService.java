package com.dardan.microservices.productservicecommand.service;

import com.dardan.commonmodels.entity.ProductEntity;
import com.dardan.microservices.productservicecommand.model.dto.ProductDTO;
import com.dardan.microservices.productservicecommand.service.repository.ProductRepository;
import com.dardan.microservices.productservicecommand.util.KafkaUtil;
import com.dardan.microservices.productservicecommand.util.UtilMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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
        kafkaUtil.sendMessage(productEntity);
        productRepository.save(productEntity);
        productDTO.setPort(port);
        return productDTO;
    }

}
