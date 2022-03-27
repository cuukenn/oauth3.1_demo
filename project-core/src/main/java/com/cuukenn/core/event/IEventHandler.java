package com.cuukenn.core.event;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * 仅声明
 *
 * @author changgg
 */
public interface IEventHandler {
    /**
     * 注册事件处理器
     */
    @PostConstruct
    default void register() {
        EventFactory.register(this);
    }

    /**
     * 注销事件处理器
     */
    @PreDestroy
    default void unregister() {
        EventFactory.unRegister(this);
    }
}
