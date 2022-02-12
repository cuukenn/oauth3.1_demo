package com.project.ums.server.poj.command;

import com.project.core.base.BaseCommand;
import com.project.core.constant.Constant;
import com.project.core.exception.BizException;
import com.project.core.util.Assert;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

/**
 * 修改用户密码
 *
 * @author changgg
 */
@Data
public class ChangePasswordCommand implements BaseCommand {
    private static final long serialVersionUID = 7879059031607352153L;
    /**
     * 旧密码
     */
    @NotBlank
    private String oldPassword;
    /**
     * 新密码
     */
    @NotBlank
    private String newPassword;
    /**
     * 新密码二次确认
     */
    @NotBlank
    private String newPasswordConfirm;

    /**
     * 校验两次输入的密码是否一致
     *
     * @return bool
     */
    public boolean validConfirm() {
        return Objects.equals(newPassword, newPasswordConfirm);
    }

    /**
     * 校验新旧密码是否不同
     *
     * @return bool
     */
    public boolean validNotSameWithOld() {
        return Objects.equals(newPassword, oldPassword);
    }

    /**
     * 校验新密码不应该为初始密码
     *
     * @return bool
     */
    public boolean validNotInitPassword() {
        return Objects.equals(newPassword, Constant.INIT_PASSWORD);
    }

    /**
     * 校验并下断言
     */
    public void validAndAssert() {
        Assert.isTrue(this.validConfirm(), () -> new BizException("新密码二次确认不一致"));
        Assert.isTrue(this.validNotSameWithOld(), () -> new BizException("新旧密码不应该一致"));
        Assert.isTrue(this.validNotInitPassword(), () -> new BizException("新密码不应该为初始密码"));
    }
}