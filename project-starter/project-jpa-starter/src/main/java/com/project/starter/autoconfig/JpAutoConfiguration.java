package com.project.starter.autoconfig;

import com.project.core.constant.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
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
     * 非security环境时使用
     *
     * @return bean
     */
    @Bean
    @ConditionalOnMissingBean(AuditorAware.class)
    public AuditorAware<String> auditorAwareWithoutSecurity() {
        log.info("register jpa auditorAware without security");
        return () -> Optional.of(Constant.ANONYMOUS);
    }
}
