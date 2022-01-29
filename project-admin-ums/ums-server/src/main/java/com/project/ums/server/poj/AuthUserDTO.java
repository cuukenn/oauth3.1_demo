package com.project.ums.server.poj;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.core.base.BaseDTO;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * 用户认证信息DTO
 *
 * @author changgg
 */
@RequiredArgsConstructor
@ToString
public class AuthUserDTO implements BaseDTO {

    private static final long serialVersionUID = -7604570599825197680L;
    /**
     * 令牌
     */
    @JsonProperty("token")
    private final String token;
    /**
     * 刷新令牌
     */
    @JsonProperty("refreshToken")
    private final String refreshToken;
}
