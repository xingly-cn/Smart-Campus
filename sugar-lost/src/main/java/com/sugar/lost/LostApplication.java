package com.sugar.lost;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.sugar"})
@EnableDiscoveryClient
public class LostApplication {
    public static void main(String[] args) {
        SpringApplication.run(LostApplication.class,args);
    }
}
