package com.wenjs.event.handler.aop;

import com.wenjs.event.handler.EventHandlerFactory;
import com.wenjs.event.handler.constant.EventConstant;
import com.wenjs.event.handler.model.EventData;
import com.wenjs.event.handler.model.EventDataManager;
import com.wenjs.event.handler.model.EventParams;
import com.wenjs.event.handler.model.EventResult;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * @author wenjs
 * @Description:
 * @date 2020/8/31 16:15
 */
@Component
@Aspect
public class EventAop {

    @Autowired
    private EventHandlerFactory eventHandlerFactory;

    @Pointcut("@annotation(com.wenjs.event.handler.annotition.EventHandler)")
    private void point() {
    }


    @Around("point()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        eventHandler(args, EventConstant.PRE_EVENT);
        Object result = joinPoint.proceed(args);
        eventHandler(args,EventConstant.POST_EVENT);
        return result;
    }

    /**
     * 事件分发处理
     * @param args
     * @param type
     */
    private void eventHandler(Object[] args,String type){

        if (args == null || args.length == 0) {
            return;
        }

        for (Object arg : args) {
            if (EventParams.class.isAssignableFrom(arg.getClass())) {
                EventParams eventParams = (EventParams) arg;
                List<HashMap<String, Object>> eventMaps = eventParams.getEvents();
                if (eventMaps == null || eventMaps.isEmpty()) {
                    continue;
                }

                for (HashMap map : eventMaps) {
                    String name = String.valueOf(map.get(EventConstant.EVENT_NAME));
                    String eventType = String.valueOf(map.get(EventConstant.EVENT_TYPE));
                    if(type.equals(eventType)){
                        EventResult result = eventHandlerFactory.getEventHandler(name).exec(map);
                        if(result != null){
                            EventData eventData = result.getResult();
                            eventData.setName(name);
                            eventData.setType(eventType);
                            EventDataManager.putEventData(name,eventData);
                        }
                    }
                }
            }
        }
    }

}
