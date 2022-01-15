package com.project.security.autoconfig;

import cn.hutool.extra.spring.EnableSpringUtil;
import com.project.security.component.JwtAccessDeniedHandler;
import com.project.security.component.JwtAuthenticationEntryPoint;
import com.project.security.component.ServerWebMvcConfigurer;
import com.project.security.component.TokenProvider;
import com.project.security.config.JwtProperties;
import com.project.security.service.ICaptchaService;
import com.project.security.service.IOnlineUserService;
import com.project.security.service.impl.InMemoryCaptchaServiceImpl;
import com.project.security.service.impl.InMemoryOnLineUserServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author changgg
 */
@Configuration
@ConditionalOnBean(WebSecurityConfigurerAdapter.class)
@EnableSpringUtil
@EnableConfigurationProperties(JwtProperties.class)
public class SecurityAutoConfiguration {
    /**
     * 验证码存储服务
     *
     * @return bean实例
     */
    @Bean
    @ConditionalOnMissingBean(ICaptchaService.class)
    public ICaptchaService captchaService() {
        return new InMemoryCaptchaServiceImpl();
    }

    /**
     * 在线服务
     *
     * @return bean实例
     */
    @Bean
    @ConditionalOnMissingBean(IOnlineUserService.class)
    public IOnlineUserService onlineUserService() {
        return new InMemoryOnLineUserServiceImpl();
    }

    /**
     * web服务
     *
     * @return bean实例
     */
    @Bean
    @ConditionalOnMissingBean(WebMvcConfigurer.class)
    public WebMvcConfigurer webMvcConfigurer() {
        return new ServerWebMvcConfigurer();
    }

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

    /**
     * 未登陆处理器
     *
     * @return bean实例
     */
    @Bean
    @ConditionalOnMissingBean(AuthenticationEntryPoint.class)
    public AuthenticationEntryPoint getAuthenticationEntryPoint() {
        return new JwtAuthenticationEntryPoint();
    }

    /**
     * 无授权处理器
     *
     * @return bean实例
     */
    @Bean
    @ConditionalOnMissingBean(AccessDeniedHandler.class)
    public AccessDeniedHandler getAccessDeniedHandler() {
        return new JwtAccessDeniedHandler();
    }

    /**
     * 令牌服务
     *
     * @return bean实例
     */
    @Bean
    @ConditionalOnMissingBean(TokenProvider.class)
    public TokenProvider tokenProvider(JwtProperties properties) {
        return new TokenProvider(properties);
    }
}
