package com.project.auth.boot.web;

import com.project.auth.boot.config.JwtProperties;
import com.project.core.api.ApiResult;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author changgg
 */
@RestController
@RequestMapping(value = "/api/auth")
@RequiredArgsConstructor
@EnableConfigurationProperties(JwtProperties.class)
public class AuthenticationController {
    private final JwtProperties jwtProperties;

    /**
     * 获取JWT公钥
     *
     * @return 数据
     */
    @GetMapping(value = "/getPublicKey")
    public ApiResult<String> getPublicKey() {
        return ApiResult.success(jwtProperties.getPublicSecret());
    }
}
