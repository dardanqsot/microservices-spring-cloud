package com.dardan.microservices.clientserviceribbon;

import com.dardan.microservices.clientserviceribbon.config.RibbonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@RibbonClient(name = "user-service-dardan", configuration = RibbonConfig.class)
@SpringBootApplication
public class ClientServiceRibbonApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientServiceRibbonApplication.class, args);
    }

}
