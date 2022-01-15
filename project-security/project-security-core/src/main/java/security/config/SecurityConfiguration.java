package com.project.security.config;

import com.project.security.component.JwtAccessDeniedHandler;
import com.project.security.component.JwtAuthenticationEntryPoint;
import com.project.security.component.TokenConfigurer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * @author changgg
 */
public class SecurityConfiguration {
    /**
     * 无感知多密码器共存并更新到默认算法
     *
     * @return 密码加密器
     */
    @Bean
    @ConditionalOnMissingBean(PasswordEncoder.class)
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    /**
     * 授权前缀
     *
     * @return 授权前缀实例
     */
    @Bean
    @ConditionalOnMissingBean(GrantedAuthorityDefaults.class)
    public GrantedAuthorityDefaults grantedAuthorityDefaults() {
        return new GrantedAuthorityDefaults("");
    }

    @Bean
    @ConditionalOnMissingBean(AuthenticationEntryPoint.class)
    public AuthenticationEntryPoint getAuthenticationEntryPoint() {
        return new JwtAuthenticationEntryPoint();
    }

    @Bean
    @ConditionalOnMissingBean(AccessDeniedHandler.class)
    public AccessDeniedHandler getAccessDeniedHandler() {
        return new JwtAccessDeniedHandler();
    }

    @Bean
    @ConditionalOnMissingBean(TokenConfigurer.class)
    public TokenConfigurer tokenConfigurer() {
        return new TokenConfigurer();
    }
}
