package com.cuukenn.core.exception;

import com.cuukenn.core.api.ApiCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * API异常
 *
 * @author changgg
 */
@Data
@EqualsAndHashCode(callSuper = true)
public final class ApiException extends BizException {
    private static final long serialVersionUID = 1371863909518429882L;
    /**
     * 状态码
     */
    private final ApiCode code;
    /**
     * 状态附加信息
     */
    private String detail;

    /**
     * 不允许使用message的方式创建实例
     *
     * @param message 消息
     */
    private ApiException(String message) {
        super(message);
        this.code = null;
    }

    public ApiException(ApiCode apiCode) {
        super(apiCode.getMsg());
        this.code = apiCode;
    }

    public ApiException(ApiCode apiCode, String detail) {
        this(apiCode);
        this.detail = detail;
    }
}