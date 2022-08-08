package com.cuukenn.auth.boot;

import com.cuukenn.ums.api.UserManageFeignClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author changgg
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackageClasses = {UserManageFeignClient.class})
public class AuthBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthBootApplication.class, args);
    }
}
