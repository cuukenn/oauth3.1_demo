package com.cuukenn.ums.pojo.query;

import com.cuukenn.core.base.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * 验证码请求
 *
 * @author changgg
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CaptchaCodeQuery implements BaseQuery {
    private static final long serialVersionUID = -3783166218296538723L;
    @Max(192)
    @Min(130)
    private Integer captchaWidth;
    @Max(108)
    @Min(48)
    private Integer captchaHeight;
}
