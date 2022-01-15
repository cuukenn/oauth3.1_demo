package com.project.ums.server.poj;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * 用户密码认证Query
 *
 * @author changgg
 */
@Data
@ToString
public class PasswordAuthQuery implements Serializable {
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
    private boolean rememberMe;
}
