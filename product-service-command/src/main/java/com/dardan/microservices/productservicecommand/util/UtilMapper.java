package com.dardan.microservices.productservicecommand.util;


import com.dardan.commonmodels.entity.ProductEntity;
import com.dardan.microservices.productservicecommand.model.dto.ProductDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class UtilMapper {

    public ProductEntity convertDTOtoEntity(ProductDTO productDTO) {
        ProductEntity productEntity = ProductEntity.builder().build();
        BeanUtils.copyProperties(productDTO, productEntity);
        return productEntity;
    }


    public ProductDTO convertEntityToDTO(ProductEntity productEntity) {
        ProductDTO productDTO = ProductDTO.builder().build();
        BeanUtils.copyProperties(productEntity, productDTO);
        return productDTO;
    }

}
