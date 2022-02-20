package com.project.ums.server.poj.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.core.base.BaseDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * 验证码DTO
 *
 * @author changgg
 */
@RequiredArgsConstructor
@ToString
@Data
public class CaptchaDTO implements BaseDTO {
    private static final long serialVersionUID = -134531838825915131L;
    /**
     * 验证码ID
     */
    @JsonProperty("id")
    private final String id;
    /**
     * 验证码图片base64数据
     */
    @JsonProperty("img_base64")
    private final String imgBase64;
}
