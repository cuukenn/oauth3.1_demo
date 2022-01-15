package com.project.common.api;

/**
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
