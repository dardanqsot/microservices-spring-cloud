package com.dardan.microservices.productservicecommand.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

//@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    private String productId;
    @NotBlank
    private String productName;
    private Long price;
    private Integer stock;
    private String productType;
    private Integer port;


}
