package com.project.ums.server.dto;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @author changgg
 */
@Data
@ToString
public class AuthUserDTO implements Serializable {
    private static final long serialVersionUID = 8705202831771159629L;
    /**
     * 用户名
     */
    @NotEmpty
    private String username;
    /**
     * 密码
     */
    @NotEmpty
    private String password;
    /**
     * 验证码ID
     */
    @NotEmpty
    private String captchaId;
    /**
     * 用户输入验证码
     */
    private String captchaCode;
    /**
     * 是否记住我
     */
    private Boolean rememberMe;
}
