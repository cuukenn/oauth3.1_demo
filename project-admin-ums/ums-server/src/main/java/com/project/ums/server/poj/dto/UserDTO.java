package com.project.ums.server.poj.dto;

import com.project.core.base.BaseDTO;
import com.project.core.validate.Telephone;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import java.time.LocalDateTime;

/**
 * 用户实体传输对象
 *
 * @author changgg
 */
@RequiredArgsConstructor
@ToString
@Data
public class UserDTO implements BaseDTO {
    private static final long serialVersionUID = 2164364673157210732L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 用户名
     */
    @Max(12)
    private String username;
    /**
     * 昵称
     */
    @Max(12)
    private String nickName;
    /**
     * 性别
     */
    private String sex;
    /**
     * 手机号
     */
    @Telephone
    private String telephone;
    /**
     * 电子邮件地址
     */
    @Email
    private String email;
    /**
     * 出生日期
     */
    private LocalDateTime birthDate;
}
