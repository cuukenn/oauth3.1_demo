package com.cuukenn.ums.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author changgg
 */
@SpringBootApplication
@EnableDiscoveryClient
public class UmsBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(UmsBootApplication.class, args);
    }
}
