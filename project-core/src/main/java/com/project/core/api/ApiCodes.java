package com.project.core.api;

/**
 * 预定义的一些返回code类型
 *
 * @author changgg
 */
public final class ApiCodes {
    /**
     * 正常状态固定值、不可修改
     */
    public static final ApiCode OK = new ApiCodeWrapper("OK", "正常");
    /**
     * 异常返回值
     */
    public static final ApiCode ERROR = new ApiCodeWrapper("ERROR", "异常");
    /**
     * 参数异常
     */
    public static final ApiCode PARAM_ERROR = new ApiCodeWrapper("PARAM_ERROR", "参数校验失败,请检查输入");
    /**
     * 未登陆
     */
    public static final ApiCode UNAUTHORIZED = new ApiCodeWrapper("UNAUTHORIZED", "当前未登陆，请登陆后再试");
    /**
     * 未授权
     */
    public static final ApiCode FORBIDDEN = new ApiCodeWrapper("FORBIDDEN", "该用户无当前资源无授权");
}
