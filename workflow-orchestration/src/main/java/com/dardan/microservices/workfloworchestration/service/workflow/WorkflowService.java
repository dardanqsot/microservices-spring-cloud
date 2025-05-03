package com.dardan.microservices.workfloworchestration.service.workflow;

import com.dardan.microservices.workfloworchestration.request.OrderRequest;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface WorkflowService {

    // Initial step
    @WorkflowMethod
    void signalCreateOrder(OrderRequest orderRequest);


}
