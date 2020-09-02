package com.wenjs.event.handler.impl;

import com.wenjs.event.handler.AbstractEventHandler;
import com.wenjs.event.handler.annotition.EventTypeSign;
import com.wenjs.event.handler.model.Event;
import com.wenjs.event.handler.model.EventResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author wenjs
 * @Description:
 * @date 2020/8/31 17:11
 */
@EventTypeSign("DEFAULT")
@Component
public class DefaultEventHandler extends AbstractEventHandler<Event> {
    private Logger logger =  LoggerFactory.getLogger(DefaultEventHandler.class);

    @Override
    protected EventResult preProcess(Event event) {
        logger.debug("DefaultEventHandler -----------------------> preProcess");

        return null;
    }

    @Override
    protected EventResult postProcess(Event event) {
        logger.debug("DefaultEventHandler -----------------------> postProcess");

        return null;
    }
}
