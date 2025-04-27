package com.dardan.microservices.productservicecommand.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StubDTO {

    private String productId;
    private String productName;

}
