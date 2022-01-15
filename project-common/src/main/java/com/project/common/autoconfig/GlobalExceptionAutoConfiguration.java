package com.project.common.autoconfig;

import com.project.common.exception.GlobalExceptionAdvice;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 全局自动装配
 *
 * @author changgg
 */
@Configuration
@ConditionalOnMissingBean(GlobalExceptionAdvice.class)
@ConditionalOnProperty(prefix = "app.common.exception", value = "global-exception-enable", havingValue = "true", matchIfMissing = true)
public class GlobalExceptionAutoConfiguration {
    @Bean
    @ConditionalOnClass(ExceptionHandler.class)
    public GlobalExceptionAdvice globalExceptionAdvice() {
        return new GlobalExceptionAdvice();
    }
}
