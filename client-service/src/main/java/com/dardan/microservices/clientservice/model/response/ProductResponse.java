package com.dardan.microservices.clientservice.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {

    private String productId;
    private String productName;
    private String productType;
    private Long price;
    private Integer stock;

}
