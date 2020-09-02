package com.wenjs.event.handler;

import com.wenjs.event.handler.constant.EventConstant;
import com.wenjs.event.handler.model.Event;
import com.wenjs.event.handler.model.EventDataManager;
import com.wenjs.event.handler.model.EventResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wenjs
 * @Description:
 * @date 2020/9/1 9:35
 */
public abstract class AbstractEventHandler<E extends Event> implements BaseEventHandler {
    private Logger logger = LoggerFactory.getLogger(AbstractEventHandler.class);

    /**
     * 泛型的 class，方便后续执行事件时，实例化事件参数
     */
    Class<E> clazz;

    public AbstractEventHandler() {

        // generic 泛型
        Type type = this.getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            ParameterizedType ptype = (ParameterizedType) type;
            clazz = (Class<E>) ptype.getActualTypeArguments()[0];
        }
    }

    @Override
    public EventResult exec(Map eventMap) {

        E event = null;
        try {
            Map<String, Object> param = parseParams(eventMap);

            event = clazz.newInstance();
            org.apache.commons.beanutils.BeanUtils.populate(event, param);
        } catch (InstantiationException e) {
            //执行全局异常

        } catch (IllegalAccessException e) {
            //执行全局异常

        } catch (InvocationTargetException e) {
            //执行全局异常

        }

        if (EventConstant.PRE_EVENT.equals(event.getType())) {
            return preProcess(event);
        } else if (EventConstant.POST_EVENT.equals(event.getType())) {
            return postProcess(event);
        } else {
            logger.debug("AbstractEventHandler ------------------------------>未知的事件类型");

            //执行全局异常

        }

        return new EventResult();
    }

    private Map parseParams(Map source) {
        Map<String, Object> param = new HashMap<>();

        param.put(EventConstant.EVENT_NAME, source.get(EventConstant.EVENT_NAME));
        param.put(EventConstant.EVENT_TYPE, source.get(EventConstant.EVENT_TYPE));

        //解析参数列表
        List<Map> params = (List<Map>) source.get(EventConstant.EVENT_PARAMS);

        if (params != null && !params.isEmpty()) {
            for (Map map : params) {
                if (EventConstant.EVENT_PARAMS_FIELD_TYPE_UNBANDING.equals(String.valueOf(map.get(EventConstant.EVENT_PARAMS_FIELD_TYPE)))) {
                    //非绑定字段处理
                    param.put(String.valueOf(map.get(EventConstant.EVENT_PARAMS_FIELD_KEY)),map.get(EventConstant.EVENT_PARAMS_FIELD_VALUE));
                }else if(EventConstant.EVENT_PARAMS_FIELD_TYPE_BANDING.equals(String.valueOf(map.get(EventConstant.EVENT_PARAMS_FIELD_TYPE)))){
                    //绑定字段处理
                    Object actualvalue = map.get(EventConstant.EVENT_PARAMS_FIELD_TYPE_ACTUAL_VALUE);
                    if(actualvalue != null){
                        //存在实际值，则直接填写
                        param.put(String.valueOf(map.get(EventConstant.EVENT_PARAMS_FIELD_KEY)),actualvalue);
                    }else{
                        //在业务数据缓存中查找，这部分数据需要我们手动填写进去
                        Object businessData = EventDataManager.getBusinessData(String.valueOf(map.get(EventConstant.EVENT_PARAMS_FIELD_VALUE)));
                        param.put(String.valueOf(map.get(EventConstant.EVENT_PARAMS_FIELD_KEY)),businessData);
                    }
                }else{

                    //其他类型字段处理
                }
            }
        }

        return param;
    }

    /**
     * 前置事件，由子类实现
     *
     * @param event
     */
    protected abstract EventResult preProcess(E event);


    /**
     * 后置事件，由子类实现
     *
     * @param event
     */
    protected abstract EventResult postProcess(E event);

}
