package com.project.core.exception;

/**
 * 业务异常基类
 *
 * @author changgg
 */
public class BizException extends RuntimeException {
    private static final long serialVersionUID = -1276339508521372134L;

    /**
     * 被子类的@Data注解使用
     */
    @SuppressWarnings("unused")
    public BizException() {
    }

    public BizException(String message) {
        super(message);
    }
}
