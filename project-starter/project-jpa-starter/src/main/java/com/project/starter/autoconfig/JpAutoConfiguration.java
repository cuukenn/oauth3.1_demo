package com.project.starter.autoconfig;

import com.project.core.constant.Constant;
import com.project.security.util.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

/**
 * @author changgg
 */
@Configuration
@EnableJpaAuditing
@Slf4j
public class JpAutoConfiguration {
    /**
     * 处于security环境时注册
     *
     * @return bean
     */
    @Bean
    @ConditionalOnMissingBean(AuditorAware.class)
    @ConditionalOnClass(SecurityUtils.class)
    public AuditorAware<String> auditorAwareInSecurity() {
        log.info("register jpa auditorAware in security");
        return () -> Optional.ofNullable(SecurityUtils.hasAuthentication() ? SecurityUtils.getCurrentUsername() : Constant.ANONYMOUS);
    }

    /**
     * 非security环境时使用
     *
     * @return bean
     */
    @Bean
    @ConditionalOnMissingBean(AuditorAware.class)
    @ConditionalOnMissingClass("com.project.security.util.SecurityUtils")
    public AuditorAware<String> auditorAwareWithoutSecurity() {
        log.info("register jpa auditorAware without security");
        return () -> Optional.of(Constant.ANONYMOUS);
    }
}
