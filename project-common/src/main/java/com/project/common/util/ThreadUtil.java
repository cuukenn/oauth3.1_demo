package com.project.common.util;

/**
 * @author changgg
 */
public final class ThreadUtil {
    /**
     * 使用默认值获取合适的线程大小
     *
     * @return 线程大小
     */
    public static int getThreadPoolCoreSize() {
        return getThreadPoolCoreSize(10, 20, 1.2F);
    }

    /**
     * 获取合适的线程大小
     *
     * @param min   最小值
     * @param max   最大值
     * @param scale 放大比例
     * @return 线程大小
     */
    public static int getThreadPoolCoreSize(int min, int max, float scale) {
        int core = (int) (Runtime.getRuntime().availableProcessors() * scale);
        if (core <= min) {
            return min;
        }
        return Math.min(max, core);
    }
}
