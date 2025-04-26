package com.dardan.microservices.productservice.model.dto;

import lombok.*;

//@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private String productId;
    private String productName;
    private Long price;
    private Integer stock;
    private String productType;
    private Integer port;


}
