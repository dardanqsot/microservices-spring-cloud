package com.dardan.microservices.workfloworchestration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class WorkflowOrchestrationApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkflowOrchestrationApplication.class, args);
    }

}
