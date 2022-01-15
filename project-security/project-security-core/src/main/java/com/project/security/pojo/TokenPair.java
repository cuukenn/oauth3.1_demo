package com.project.security.pojo;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotEmpty;

/**
 * @author changgg
 */
@RequiredArgsConstructor
@Data
public class TokenPair {
    /**
     * 令牌
     */
    @NotEmpty
    private final String token;
    /**
     * 刷新令牌
     */
    @NotEmpty
    private final String refreshToken;
}