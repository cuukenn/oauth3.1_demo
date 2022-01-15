package com.project.common.api;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author changgg
 */
@Data
@Builder
public class ApiResult<T> implements Serializable {
    private static final long serialVersionUID = -5205542432444366520L;
    private String code;
    private String message;
    private T data;

    /**
     * 简单状态封装
     *
     * @param apiCode apiCode
     * @return 结果
     */
    public static ApiResult<Object> of(ApiCode apiCode) {
        return ApiResult.builder().code(apiCode.getCode()).message(apiCode.getMsg()).build();
    }

    /**
     * 简单状态封装
     *
     * @param apiCode apiCode
     * @return 结果
     */
    public static ApiResult<Object> of(ApiCode apiCode, String message) {
        return ApiResult.builder().code(apiCode.getCode()).message(apiCode.getMsg() + ",附加信息:" + message).build();
    }

    /**
     * 普通数据封装
     *
     * @param payload apiCode
     * @return 结果
     */
    @SuppressWarnings("unchecked")
    public static <T> ApiResult<T> of(T payload) {
        return (ApiResult<T>) ApiResult.builder().code(ApiCodes.OK.getCode()).message(ApiCodes.OK.getMsg()).data(payload).build();
    }

    /**
     * 分页数据封装
     *
     * @param pageNumber 页码
     * @param pageSize   页大小
     * @param total      总计
     * @param payload    荷载
     * @return 结果
     */
    @SuppressWarnings("unchecked")
    public static <T> ApiResult<T> of(int pageNumber, int pageSize, int total, T payload) {
        ApiPage<Object> page = ApiPage.builder().pageNumber(pageNumber).pageSize(pageSize).total(total).list(payload).build();
        return (ApiResult<T>) ApiResult.builder().code(ApiCodes.OK.getCode()).message(ApiCodes.OK.getMsg()).data(page).build();
    }
}
