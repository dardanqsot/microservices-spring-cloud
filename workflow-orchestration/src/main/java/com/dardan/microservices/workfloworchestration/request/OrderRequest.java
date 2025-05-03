package com.dardan.microservices.workfloworchestration.request;

import lombok.Data;

@Data
public class OrderRequest {

    private String workflowId;
    private String orderId;
    private String productId;
    private Integer quantity;

}
