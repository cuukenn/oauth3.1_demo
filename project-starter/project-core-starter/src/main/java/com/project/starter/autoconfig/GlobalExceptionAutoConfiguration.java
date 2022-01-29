package com.project.starter.autoconfig;

import com.project.starter.component.GlobalExceptionAdvice;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 全局自动装配
 *
 * @author changgg
 */
@Configuration
public class GlobalExceptionAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean(GlobalExceptionAdvice.class)
    public GlobalExceptionAdvice globalExceptionAdvice() {
        return new GlobalExceptionAdvice();
    }
}
