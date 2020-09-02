package com.wenjs.event.handler.model;

import org.springframework.core.NamedThreadLocal;

import java.util.Map;

/**
 * @author wenjs
 * @Description: 事件数据资源管理
 * @date 2020/9/1 16:13
 */
public class EventDataManager {

    private static final ThreadLocal<Map<String,EventData>> eventData = new NamedThreadLocal("event data resources");

    //业务数据缓存
    private static final ThreadLocal<Map<String,Object>> businessData = new NamedThreadLocal("event data resources");


    public static EventData getEventData(String eventName){
        return eventData.get().get(eventName);
    }

    public static void putEventData(String eventName,EventData event){
        eventData.get().put(eventName,event);
    }

    public static Object getBusinessData(String businessName){
        return businessData.get().get(businessName);
    }

    public static void putBusinessData(String businessName,Object data){
        businessData.get().put(businessName,data);
    }
}
