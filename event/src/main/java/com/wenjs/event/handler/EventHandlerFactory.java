package com.wenjs.event.handler;

import com.wenjs.event.handler.annotition.EventTypeSign;
import com.wenjs.event.handler.constant.EventConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author wenjs
 * @Description: 执行事件的工厂类
 * @date 2020/8/31 17:10
 */
@Component
public class EventHandlerFactory {

    private Map<String, BaseEventHandler> eventHandlerCache;

    @Autowired
    public void init(List<BaseEventHandler> handlers) {
        eventHandlerCache = new HashMap<>();
        if (handlers != null && !handlers.isEmpty()) {
            for (BaseEventHandler handler : handlers) {
                EventTypeSign annotation = handler.getClass().getAnnotation(EventTypeSign.class);
                if (annotation != null) {
                    eventHandlerCache.put(annotation.value(), handler);
                }
            }
        }
    }

    public BaseEventHandler getEventHandler(String eventName) {
        Objects.requireNonNull(eventHandlerCache, "事件处理器未初始化");
        BaseEventHandler instance = eventHandlerCache.get(eventName);

        Objects.requireNonNull(instance, "未知的事件类型："+eventName);
        return instance;
    }
}
