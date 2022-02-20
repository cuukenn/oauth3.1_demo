package com.project.auth.boot;

import com.project.ums.api.OAuthClientFeignClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author changgg
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackageClasses = {OAuthClientFeignClient.class})
public class AuthBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthBootApplication.class, args);
    }
}
