package com.project.ums.server.poj;

import com.project.core.base.BaseCommand;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

/**
 * 用户密码认证Query
 *
 * @author changgg
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "password")
public class PasswordAuthCommand implements BaseCommand {
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
