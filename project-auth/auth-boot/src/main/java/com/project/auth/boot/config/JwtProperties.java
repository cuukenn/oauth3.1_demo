package com.project.auth.boot.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * jwt配置
 *
 * @author changgg
 */
@Data
@ConfigurationProperties(prefix = "app.jwt")
public class JwtProperties {
    /**
     * RSA公钥(base64)
     */
    private String publicSecret;
    /**
     * RSA私钥(base64)
     */
    private String privateSecret;
}
