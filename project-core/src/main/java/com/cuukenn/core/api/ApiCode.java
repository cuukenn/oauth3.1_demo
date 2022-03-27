package com.cuukenn.core.api;

/**
 * 全局状态接口
 *
 * @author changgg
 */
public interface ApiCode {
    /**
     * 状态码
     *
     * @return 状态码
     */
    String getCode();

    /**
     * 状态详情
     *
     * @return 状态详情
     */
    String getMsg();
}
