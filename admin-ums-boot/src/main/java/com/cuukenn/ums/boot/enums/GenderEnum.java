package com.cuukenn.ums.boot.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * @author changgg
 */
@RequiredArgsConstructor
@Getter
public enum GenderEnum {
    /**
     * 未设置
     */
    NOT_SET(-1, "未设置"),
    /**
     * 保密
     */
    SECRET(0, "保密"),
    /**
     * 男性
     */
    MALE(1, "男"),
    /**
     * 女性
     */
    FEMALE(2, "女");
    private final Integer value;
    private final String desc;
}
