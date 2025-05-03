package com.dardan.microservices.workfloworchestration.config;

import com.dardan.microservices.workfloworchestration.proxy.OrderFeign;
import com.dardan.microservices.workfloworchestration.proxy.ProductFeign;
import com.dardan.microservices.workfloworchestration.service.activity.ActivityService;
import com.dardan.microservices.workfloworchestration.service.activity.ActivityServiceImpl;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowClientOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.serviceclient.WorkflowServiceStubsOptions;
import io.temporal.worker.WorkerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class TemporalConfig {

    private final ProductFeign productFeign;
    private final OrderFeign orderFeign;

    @Value("${temporal.dardan.hostname:127.0.0.1:7233}")
    private String HOSTNAME;

    @Value("${temporal.dardan.namespace:default}")
    private String NAMESPACE;

    public static String QUEUE_NAME = "dardan";

    @Bean
    public WorkflowServiceStubs workflowServiceStubs() {
        return WorkflowServiceStubs.newInstance(WorkflowServiceStubsOptions.newBuilder()
                .setTarget(HOSTNAME).build());
    }

    @Bean
    public WorkflowClient workflowClient(WorkflowServiceStubs workflowServiceStubs) {
        return WorkflowClient.newInstance(workflowServiceStubs, WorkflowClientOptions.newBuilder()
                .setNamespace(NAMESPACE).build());
    }

    @Bean
    public WorkerFactory workerFactory(WorkflowClient workflowClient) {
        return WorkerFactory.newInstance(workflowClient);
    }

    @Bean
    public ActivityService signUpActivity(){
        return new ActivityServiceImpl(productFeign, orderFeign);
    }

}
