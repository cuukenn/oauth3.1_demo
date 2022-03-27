package com.cuukenn.core.event;

import cn.hutool.core.thread.NamedThreadFactory;
import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.cuukenn.core.util.ThreadUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 事件工厂
 *
 * @author changgg
 */
public final class EventFactory {
    /**
     * 发布事件
     *
     * @param event 事件
     * @param async 是否异步
     */
    public static void dispatcher(IEvent event, boolean async) {
        if (async) {
            asyncDispatcher(event);
        } else {
            syncDispatcher(event);
        }
    }

    /**
     * 同步发布事件
     *
     * @param event 事件
     */
    public static void syncDispatcher(IEvent event) {
        SyncBusHolder.EVENT_BUS.post(event);
    }

    /**
     * 异步发布事件
     *
     * @param event 事件
     */
    public static void asyncDispatcher(IEvent event) {
        AsyncBusHolder.EVENT_BUS.post(event);
    }

    /**
     * 注册事件处理器
     *
     * @param handler 处理器
     */
    public static void register(IEventHandler handler) {
        SyncBusHolder.EVENT_BUS.register(handler);
        AsyncBusHolder.EVENT_BUS.register(handler);
    }

    /**
     * 取消事件处理器
     *
     * @param handler 处理器
     */
    public static void unRegister(IEventHandler handler) {
        SyncBusHolder.EVENT_BUS.unregister(handler);
        AsyncBusHolder.EVENT_BUS.unregister(handler);
    }

    /**
     * 单例
     */
    static class AsyncBusHolder {
        static final ExecutorService EXECUTOR_SERVICE = new ThreadPoolExecutor(
                ThreadUtil.getThreadPoolCoreSize(), ThreadUtil.getThreadPoolCoreSize(),
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>(5000),
                new NamedThreadFactory("event-handler-", true),
                new ThreadPoolExecutor.CallerRunsPolicy());
        static final AsyncEventBus EVENT_BUS = new AsyncEventBus(EXECUTOR_SERVICE);
    }

    /**
     * 单例
     */
    static class SyncBusHolder {
        static final EventBus EVENT_BUS = new EventBus();
    }
}
