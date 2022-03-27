package com.cuukenn.core.api;

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
    /**
     * 账户密码过期、强制修改密码
     */
    public static final ApiCode FORCE_CHANG_PASSWORD = new ApiCodeWrapper("FORBIDDEN", "用户密码过期、需修改密码后方可登陆");
    /**
     * 认证失败
     */
    public static final ApiCode AUTH_FAILED = new ApiCodeWrapper("AUTH_FAILED", "认证失败");
}
