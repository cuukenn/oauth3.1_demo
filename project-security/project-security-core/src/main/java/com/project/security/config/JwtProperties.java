package com.project.security.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.concurrent.TimeUnit;

/**
 * jwt配置
 *
 * @author changgg
 */
@ConfigurationProperties(prefix = "app.jwt")
@Data
public class JwtProperties {
    /**
     * 全局时间单位
     */
    private final TimeUnit timeUnit = TimeUnit.MILLISECONDS;
    /**
     * 授权头
     */
    private String header = "Authorization";
    /**
     * 授权头前缀
     */
    private String headerPrefix = "Bearer ";
    /**
     * 刷新token 参数名称
     */
    private String refreshParam = "RefreshToken";
    /**
     * 令牌前缀
     */
    private String tokenPrefix = "app:online-token";
    /**
     * 正常过期时间(默认6小时)
     */
    private long tokenInvalidateInMillSeconds = timeUnit.convert(1L, TimeUnit.HOURS);
    /**
     * 刷新令牌时间
     */
    private long refreshTokenInvalidateInMillSeconds = timeUnit.convert(1L, TimeUnit.DAYS);
    /**
     * 刷新令牌时间(记住我后的过期时间)
     */
    private long refreshTokenInvalidateRememberMeInMillSeconds = timeUnit.convert(7L, TimeUnit.DAYS);
}
