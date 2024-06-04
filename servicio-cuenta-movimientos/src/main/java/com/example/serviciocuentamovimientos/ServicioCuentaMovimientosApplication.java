package com.example.serviciocuentamovimientos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ServicioCuentaMovimientosApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServicioCuentaMovimientosApplication.class, args);
    }
}
