package com.project.common.exception;

import com.project.common.api.ApiCode;
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
    private final ApiCode code;
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
}