package com.dardan.microservices.workfloworchestration.controller;


import com.dardan.microservices.workfloworchestration.request.OrderRequest;
import com.dardan.microservices.workfloworchestration.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WorkflowController {

    private final OrderService orderService;


    @PostMapping("/start")
    public String createOrder(@RequestBody OrderRequest orderRequest) {
        return orderService.createOrder(orderRequest);
    }


}
