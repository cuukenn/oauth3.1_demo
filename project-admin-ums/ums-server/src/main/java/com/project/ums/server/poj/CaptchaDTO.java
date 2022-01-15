package com.project.ums.server.poj;

import com.project.common.poj.BaseDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * 验证码DTO
 * @author changgg
 */
@RequiredArgsConstructor
@ToString
@Data
public class CaptchaDTO implements BaseDTO {
    private static final long serialVersionUID = -134531838825915131L;
    private final String id;
    private final String imgBase64;
}
