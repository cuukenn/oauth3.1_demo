package com.project.starter.autoconfig;

import com.project.starter.aspect.UniformApiResultWarn;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author changgg
 */
@Configuration
public class ApiAutoConfiguration {
    @Bean
    @Profile("dev")
    public UniformApiResultWarn apiResponseAdvice() {
        return new UniformApiResultWarn();
    }
}
