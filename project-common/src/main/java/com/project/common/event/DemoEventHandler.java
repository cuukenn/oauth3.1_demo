package com.project.common.event;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.Subscribe;

/**
 * 事件处理器事例
 *
 * @author changgg
 */
final class DemoEventHandler implements IEventHandler {
    @Subscribe
    @AllowConcurrentEvents
    private void invoke(DemoEvent event) {
        //do something
        System.out.println(event);
    }

    static class DemoEvent implements IEvent {
    }
}
