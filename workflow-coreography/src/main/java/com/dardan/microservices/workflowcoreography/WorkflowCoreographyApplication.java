package com.dardan.microservices.workflowcoreography;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class WorkflowCoreographyApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkflowCoreographyApplication.class, args);
    }

}
