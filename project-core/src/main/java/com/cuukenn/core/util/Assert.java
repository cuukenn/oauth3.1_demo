package com.cuukenn.core.util;

import cn.hutool.core.util.StrUtil;
import com.cuukenn.core.api.ApiCode;
import com.cuukenn.core.exception.ApiException;
import com.cuukenn.core.exception.BizException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.function.Supplier;

/**
 * 断言
 *
 * @author changgg
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Assert {
    /**
     * 是否为true
     *
     * @param expression 表达式
     * @param exception  为false时的异常
     */
    public static void isTrue(boolean expression, Supplier<? extends BizException> exception) {
        if (!expression) {
            throw exception.get();
        }
    }

    /**
     * 是否为true
     *
     * @param expression 表达式
     * @param apiCode    为false时的异常code
     * @param detail     为false时的异常详情
     */
    public static void isTrue(boolean expression, ApiCode apiCode, String detail) {
        isTrue(expression, () -> new ApiException(apiCode, detail));
    }

    /**
     * 是否为true
     *
     * @param expression 表达式
     * @param apiCode    为false时的异常code
     */
    public static void isTrue(boolean expression, ApiCode apiCode) {
        isTrue(expression, () -> new ApiException(apiCode));
    }

    /**
     * 是否为true
     *
     * @param expression 表达式
     * @param exception  为false时的异常
     */
    public static void isFalse(boolean expression, Supplier<? extends BizException> exception) {
        isTrue(!expression, exception);
    }

    /**
     * 是否为false
     *
     * @param expression 表达式
     * @param apiCode    为true时的异常code
     */
    public static void isFalse(boolean expression, ApiCode apiCode) {
        isFalse(expression, () -> new ApiException(apiCode));
    }

    /**
     * 是否为false
     *
     * @param expression 表达式
     * @param apiCode    为true时的异常code
     * @param detail     为true时的异常详情
     */
    public static void isFalse(boolean expression, ApiCode apiCode, String detail) {
        isFalse(expression, () -> new ApiException(apiCode, detail));
    }

    /**
     * 是否为null
     *
     * @param object    对象
     * @param exception 不为Null时的异常
     */
    public static void isNull(Object object, Supplier<? extends BizException> exception) {
        isTrue(object == null, exception);
    }

    /**
     * 是否为null
     *
     * @param object  对象
     * @param apiCode 不为Null时的异常code
     */
    public static void isNull(Object object, ApiCode apiCode) {
        isNull(object, () -> new ApiException(apiCode));
    }

    /**
     * 是否为null
     *
     * @param object  对象
     * @param apiCode 不为Null时的异常code
     * @param detail  不为Null时的异常详情
     */
    public static void isNull(Object object, ApiCode apiCode, String detail) {
        isNull(object, () -> new ApiException(apiCode, detail));
    }

    /**
     * 是否非null
     *
     * @param object    对象
     * @param exception 为Null时的异常
     */
    public static void notNull(Object object, Supplier<? extends BizException> exception) {
        isFalse(object == null, exception);
    }

    /**
     * 是否非null
     *
     * @param object  对象
     * @param apiCode 为Null时的异常code
     */
    public static void notNull(Object object, ApiCode apiCode) {
        notNull(object, () -> new ApiException(apiCode));
    }

    /**
     * 是否非null
     *
     * @param object  对象
     * @param apiCode 为Null时的异常code
     * @param detail  为Null时的异常详情
     */
    public static void notNull(Object object, ApiCode apiCode, String detail) {
        notNull(object, () -> new ApiException(apiCode, detail));
    }

    /**
     * 字符串是否为空白
     *
     * @param str       字符串
     * @param exception 字符串非空白时的异常
     */
    public static void isBlank(CharSequence str, Supplier<? extends BizException> exception) {
        isFalse(StrUtil.isBlank(str), exception);
    }

    /**
     * 字符串是否为空白
     *
     * @param str     字符串
     * @param apiCode 字符串非空白时的异常code
     */
    public static void isBlank(CharSequence str, ApiCode apiCode) {
        isBlank(str, () -> new ApiException(apiCode));
    }

    /**
     * 字符串是否为空白
     *
     * @param str     字符串
     * @param apiCode 字符串非空白时的异常code
     * @param detail  字符串非空白时的异常详情
     */
    public static void isBlank(CharSequence str, ApiCode apiCode, String detail) {
        isBlank(str, () -> new ApiException(apiCode, detail));
    }

    /**
     * 字符串是否非空白
     *
     * @param str       字符串
     * @param exception 字符串空白时的异常
     */
    public static void notBlank(CharSequence str, Supplier<? extends BizException> exception) {
        isFalse(StrUtil.isNotBlank(str), exception);
    }

    /**
     * 字符串是否非空白
     *
     * @param str     字符串
     * @param apiCode 字符串空白时的异常code
     */
    public static void notBlank(CharSequence str, ApiCode apiCode) {
        notBlank(str, () -> new ApiException(apiCode));
    }

    /**
     * 字符串是否非空白
     *
     * @param str     字符串
     * @param apiCode 字符串空白时的异常code
     * @param detail  字符串空白时的异常详情
     */
    public static void notBlank(CharSequence str, ApiCode apiCode, String detail) {
        notBlank(str, () -> new ApiException(apiCode, detail));
    }
}
