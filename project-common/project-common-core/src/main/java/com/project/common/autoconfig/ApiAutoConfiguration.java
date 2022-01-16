package com.project.common.autoconfig;

import com.project.common.api.UniformApiResultWarn;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author changgg
 */
@Configuration
public class ApiAutoConfiguration {
    @Bean
    @ConditionalOnProperty(prefix = "app.common.api", value = "warn-enable", havingValue = "true", matchIfMissing = true)
    @ConditionalOnClass(Aspect.class)
    public UniformApiResultWarn apiResponseAdvice() {
        return new UniformApiResultWarn();
    }
}
