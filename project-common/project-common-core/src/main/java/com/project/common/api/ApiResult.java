package com.project.common.api;

import com.project.common.poj.BaseDTO;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author changgg
 */
@Data
@Builder(access = AccessLevel.MODULE)
public class ApiResult<T> implements Serializable {
    private static final long serialVersionUID = -5205542432444366520L;
    private String code;
    private String message;
    private T payload;

    /**
     * 简单状态封装
     *
     * @return 结果
     */
    public static <T> ApiResult<T> success() {
        return ApiResult.of(ApiCodes.OK);
    }

    /**
     * 简单状态封装
     *
     * @param message 消息
     * @return 结果
     */
    public static ApiResult<String> success(String message) {
        return ApiResult.of(ApiCodes.OK, message);
    }

    /**
     * 普通数据封装
     *
     * @param payload 荷载
     * @return 结果
     */
    public static ApiResult<Boolean> success(Boolean payload) {
        return ApiResult.<Boolean>builder().code(ApiCodes.OK.getCode()).message(ApiCodes.OK.getMsg()).payload(payload).build();
    }

    /**
     * 普通数据封装
     *
     * @param payload apiCode
     * @return 结果
     */
    public static <T extends BaseDTO> ApiResult<T> success(T payload) {
        return ApiResult.<T>builder().code(ApiCodes.OK.getCode()).message(ApiCodes.OK.getMsg()).payload(payload).build();
    }

    /**
     * 普通数据封装
     *
     * @param payload apiCode
     * @return 结果
     */
    public static <T extends Number> ApiResult<T> success(T payload) {
        return ApiResult.<T>builder().code(ApiCodes.OK.getCode()).message(ApiCodes.OK.getMsg()).payload(payload).build();
    }

    /**
     * 分页数据封装
     *
     * @param pageNumber 页码
     * @param pageSize   页大小
     * @param total      总计
     * @param payload    荷载(集合)
     * @return 结果
     */
    public static <T extends List<V>, V extends BaseDTO> ApiResult<ApiPage<T, V>> success(int pageNumber, int pageSize, int total, T payload) {
        return ApiResult.<ApiPage<T, V>>builder()
                .code(ApiCodes.OK.getCode()).message(ApiCodes.OK.getMsg())
                .payload(ApiPage.<T, V>builder().pageNumber(pageNumber).pageSize(pageSize).total(total).list(payload).build())
                .build();
    }

    /**
     * 简单状态封装
     *
     * @param message 消息
     * @return 结果
     */
    public static <T> ApiResult<T> fail(String message) {
        return ApiResult.<T>builder().code(ApiCodes.ERROR.getCode()).message(message).build();
    }

    /**
     * 简单状态封装
     *
     * @param apiCode apiCode
     * @return 结果
     */
    public static <T> ApiResult<T> of(ApiCode apiCode) {
        return ApiResult.<T>builder().code(apiCode.getCode()).message(apiCode.getMsg()).build();
    }

    /**
     * 简单状态封装
     *
     * @param apiCode apiCode
     * @return 结果
     */
    public static <T> ApiResult<T> of(ApiCode apiCode, String message) {
        return ApiResult.<T>builder().code(apiCode.getCode()).message(message).build();
    }
}
