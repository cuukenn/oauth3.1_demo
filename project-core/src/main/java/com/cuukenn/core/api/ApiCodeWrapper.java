package com.cuukenn.core.api;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * 作为{@link ApiCode}的一种默认实现
 *
 * @author changgg
 * @see ApiCodes,ApiResult
 */
@Data
@RequiredArgsConstructor
@ToString
public final class ApiCodeWrapper implements ApiCode {
    /**
     * 状态码
     */
    private final String code;
    /**
     * 状态描述
     */
    private final String msg;
}
