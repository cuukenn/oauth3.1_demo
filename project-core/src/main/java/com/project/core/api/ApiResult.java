package com.project.core.api;

import com.project.core.assembler.BaseAssembler;
import com.project.core.base.BaseDTO;
import com.project.core.base.BaseEntity;
import com.project.core.exception.BizException;
import com.project.core.util.Assert;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

/**
 * 全局放回值包装
 * 由当前类静态方法进行初始化、构造方法不对外开放(反序列化需要提供无参构造函数,禁止直接调用)
 *
 * @author changgg
 * @see ApiCode,ApiCodes,ApiCodeWrapper,ApiPage
 */
@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class ApiResult<T> implements BaseDTO {
    private static final long serialVersionUID = -5205542432444366520L;
    /**
     * 状态码
     */
    private String status;
    /**
     * 状态描述
     */
    private String message;
    /**
     * 时间戳
     */
    @Builder.Default()
    private long timestamp = System.currentTimeMillis();
    /**
     * 数据
     */
    private T data;

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
        return ApiResult.<String>builder().status(ApiCodes.OK.getCode()).message(ApiCodes.OK.getMsg()).data(message).build();
    }

    /**
     * 普通数据封装
     *
     * @param payload 荷载
     * @return 结果
     */
    public static ApiResult<Boolean> success(Boolean payload) {
        return ApiResult.<Boolean>builder().status(ApiCodes.OK.getCode()).message(ApiCodes.OK.getMsg()).data(payload).build();
    }

    /**
     * 普通数据封装
     *
     * @param payload apiCode
     * @return 结果
     */
    public static <T extends BaseDTO> ApiResult<T> success(T payload) {
        return ApiResult.<T>builder().status(ApiCodes.OK.getCode()).message(ApiCodes.OK.getMsg()).data(payload).build();
    }

    /**
     * 普通数据封装
     *
     * @param payload apiCode
     * @return 结果
     */
    public static <T extends Number> ApiResult<T> success(T payload) {
        return ApiResult.<T>builder().status(ApiCodes.OK.getCode()).message(ApiCodes.OK.getMsg()).data(payload).build();
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
    public static <V extends BaseDTO> ApiResult<ApiPage<V>> success(int pageNumber, int pageSize, long total, List<V> payload) {
        return ApiResult.<ApiPage<V>>builder()
                .status(ApiCodes.OK.getCode()).message(ApiCodes.OK.getMsg())
                .data(ApiPage.<V>builder().pageNumber(pageNumber).pageSize(pageSize).total(total).list(payload).build())
                .build();
    }

    /**
     * 简单状态封装
     *
     * @param message 消息
     * @return 结果
     */
    public static <T> ApiResult<T> fail(String message) {
        return ApiResult.<T>builder().status(ApiCodes.ERROR.getCode()).message(message).build();
    }

    /**
     * 简单状态封装
     *
     * @param apiCode apiCode
     * @return 结果
     */
    public static <T> ApiResult<T> of(ApiCode apiCode) {
        return ApiResult.<T>builder().status(apiCode.getCode()).message(apiCode.getMsg()).build();
    }

    /**
     * 简单状态封装
     *
     * @param apiCode apiCode
     * @return 结果
     */
    public static <T> ApiResult<T> of(ApiCode apiCode, String message) {
        return ApiResult.<T>builder().status(apiCode.getCode()).message(message).build();
    }

    /**
     * 对结果进行转换和包装
     *
     * @param entity 数据
     * @param <T>    数据载荷
     * @return 载荷
     */
    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    public static <T extends BaseEntity, D extends BaseDTO> ApiResult<D> transform(Optional<T> entity, BaseAssembler<D, T> assembler) {
        return entity.map(assembler::toDto)
                .map(ApiResult::success)
                .orElse(ApiResult.fail("查询无数据"));
    }

    /**
     * 对结果进行必要的校验
     *
     * @param data 数据
     * @param <T>  数据载荷
     * @return 载荷
     */
    public static <T> Optional<T> validate(ApiResult<T> data) {
        Assert.notNull(data, () -> new BizException("API响应数据为null"));
        Assert.isTrue(ApiCodes.OK.getCode().equals(data.getStatus()), () -> new BizException(String.format("状态码错误,code:%s,msg:%s", data.getStatus(), data.getMessage())));
        return Optional.ofNullable(data.getData());
    }
}
