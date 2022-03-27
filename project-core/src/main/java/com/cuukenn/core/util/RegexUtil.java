package com.cuukenn.core.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式实例
 * 所有Pattern实例都写到该类中统一管理
 *
 * @author changgg
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RegexUtil {
    public static final Pattern DOT = Pattern.compile(",");
    public static final Pattern BLANK = Pattern.compile("\\s+");
    public static final Pattern SPOT = Pattern.compile("\\.");
    public static final Pattern NUMBER = Pattern.compile("-?([0-9]+)(.[0-9]+)?");
    public static final Pattern MOBILE_PATTERN = Pattern.compile("1\\d{10}");

    /**
     * 获取匹配的数据
     *
     * @param str     内容
     * @param pattern 规则
     * @return 匹配数据
     */
    public static String get(String str, Pattern pattern) {
        return get(str, pattern, 0);
    }

    /**
     * 获取匹配的数据
     *
     * @param str     内容
     * @param pattern 规则
     * @param group   分组
     * @return 匹配数据
     */
    public static String get(String str, Pattern pattern, int group) {
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            return matcher.group(group);
        } else {
            return null;
        }
    }

    /**
     * 获取匹配的所有数据
     *
     * @param str     内容
     * @param pattern 规则
     * @return 匹配数据
     */
    public static List<String> getAll(String str, Pattern pattern) {
        return getAll(str, pattern, 0);
    }

    /**
     * 获取匹配的所有数据
     *
     * @param str     内容
     * @param pattern 规则
     * @param group   分组
     * @return 匹配数据
     */
    public static List<String> getAll(String str, Pattern pattern, int group) {
        Matcher matcher = pattern.matcher(str);
        List<String> rs = new ArrayList<>();
        while (matcher.find()) {
            rs.add(matcher.group(group));
        }
        return rs;
    }
}
