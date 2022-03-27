package com.cuukenn.core.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

/**
 * 时间转换工具类
 *
 * @author changgg
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DateUtil {
    public static final DateTimeFormatter PATTERN1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final DateTimeFormatter PATTERN2 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    public static final DateTimeFormatter PATTERN3 = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
    public static final DateTimeFormatter PATTERN4 = DateTimeFormatter.ofPattern("yyyy/MM/dd hh:mm:ss");
    public static final DateTimeFormatter PATTERN5 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter PATTERN6 = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    public static final DateTimeFormatter PATTERN7 = DateTimeFormatter.ofPattern("yyyyMMdd");
    public static final DateTimeFormatter PATTERN8 = DateTimeFormatter.ofPattern("HH:mm:ss");

    /**
     * 将时间字符串按照指定格式进行转换
     *
     * @param date      时间
     * @param formatter 转换格式
     * @return 时间
     */
    public static Date parseDate(String date, DateTimeFormatter formatter) {
        if (date == null || formatter == null) {
            return null;
        }
        return new Date(formatter.parse(date).getLong(ChronoField.MILLI_OF_SECOND));
    }

    /**
     * 将时间字符串按照指定格式进行转换
     *
     * @param date      时间
     * @param formatter 转换格式
     * @return 时间
     */
    public static LocalDateTime parseLocalDateTime(String date, DateTimeFormatter formatter) {
        if (date == null || formatter == null) {
            return null;
        }
        return LocalDateTime.from(formatter.parse(date));
    }

    /**
     * 将时间字符串按照指定格式进行转换
     *
     * @param date      时间
     * @param formatter 转换格式
     * @return 时间
     */
    public static String format(Date date, DateTimeFormatter formatter) {
        if (date == null) {
            return null;
        }
        return format(date.toInstant(), formatter);
    }

    /**
     * 将时间字符串按照指定格式进行转换
     *
     * @param date      时间
     * @param formatter 转换格式
     * @return 时间
     */
    public static String format(TemporalAccessor date, DateTimeFormatter formatter) {
        if (date == null || formatter == null) {
            return null;
        }
        return formatter.format(date);
    }
}
