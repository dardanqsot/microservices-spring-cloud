package com.dardan.microservices.clientservice.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

    private String productId;
    private String productName;
    private String productType;
    private Long price;
    private Integer stock;

}
