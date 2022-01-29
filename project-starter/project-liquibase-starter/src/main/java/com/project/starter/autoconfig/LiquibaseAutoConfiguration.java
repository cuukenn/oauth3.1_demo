package com.project.starter.autoconfig;

import cn.hutool.extra.spring.EnableSpringUtil;
import com.project.starter.component.LiquibaseLockRemovePostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author changgg
 */
@Configuration
@EnableSpringUtil
public class LiquibaseAutoConfiguration {
    @Bean
    @ConditionalOnProperty(prefix = "app.core.liquibase", value = "unlock-when-start", havingValue = "true")
    public BeanPostProcessor liquibaseLockRemovePostProcessor() {
        return new LiquibaseLockRemovePostProcessor();
    }
}
