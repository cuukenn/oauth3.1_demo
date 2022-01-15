package com.project.security.component;

import cn.hutool.extra.spring.EnableSpringUtil;
import com.project.security.config.SecurityConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter;

/**
 * @author changgg
 */
@Import(SecurityConfiguration.class)
@EnableSpringUtil
@SuppressWarnings("SpringJavaAutowiredMembersInspection")
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {
    @Autowired
    private TokenConfigurer tokenConfigurer;
    @Autowired
    private AccessDeniedHandler accessDeniedHandler;
    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/*.html")
                .antMatchers("/**/*.html")
                .antMatchers("/**/*.js")
                .antMatchers("/**/*.css")
                .antMatchers("/webSocket/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .requestCache(AbstractHttpConfigurer::disable)
                .headers(header -> header
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::disable)
                        .xssProtection(xss -> xss.xssProtectionEnabled(true))
                        .cacheControl(HeadersConfigurer.CacheControlConfig::disable)
                        .referrerPolicy(refer -> refer.policy(ReferrerPolicyHeaderWriter.ReferrerPolicy.ORIGIN))
                ).rememberMe(AbstractHttpConfigurer::disable)
                .authorizeRequests(auth -> auth
                        // 放行OPTIONS请求
                        .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        // 其他所有请求都需要认证
                        .anyRequest().authenticated()
                ).exceptionHandling(exception -> exception
                        .authenticationEntryPoint(authenticationEntryPoint)
                        .accessDeniedHandler(accessDeniedHandler)
                ).apply(tokenConfigurer);
    }
}
