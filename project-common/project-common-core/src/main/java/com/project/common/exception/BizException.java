package com.project.common.exception;

/**
 * 业务异常基类
 *
 * @author changgg
 */
public class BizException extends RuntimeException {
    private static final long serialVersionUID = -1276339508521372134L;

    public BizException() {
    }

    public BizException(String message) {
        super(message);
    }
}
