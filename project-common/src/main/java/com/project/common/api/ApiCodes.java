package com.project.common.api;

/**
 * 预定义的一些返回值
 *
 * @author changgg
 */
public final class ApiCodes {
    /**
     * 正常状态固定值、不可修改
     */
    public static ApiCode OK = new ApiCodeWrapper("OK", "OK");
    /**
     * 位置异常返回值
     */
    public static ApiCode ERROR = new ApiCodeWrapper("ERROR", "FAILED");
    /**
     * 参数异常
     */
    public static ApiCode PARAM_ERROR = new ApiCodeWrapper("PARAM_ERROR", "参数校验失败,请检查输入");
    /**
     * 未登陆
     */
    public static ApiCode UNAUTHORIZED = new ApiCodeWrapper("UNAUTHORIZED", "当前未登陆，请登陆后再试");
    /**
     * 未授权
     */
    public static ApiCode FORBIDDEN = new ApiCodeWrapper("FORBIDDEN", "该用户无当前资源无授权");
}
