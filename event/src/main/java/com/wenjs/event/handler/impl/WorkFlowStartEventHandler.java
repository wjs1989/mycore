package com.wenjs.event.handler.impl;

import com.wenjs.event.handler.AbstractEventHandler;
import com.wenjs.event.handler.annotition.EventTypeSign;
import com.wenjs.event.handler.model.EventData;
import com.wenjs.event.handler.model.EventResult;
import com.wenjs.event.handler.model.dto.WorkFlowStartEvent;
import com.wenjs.event.handler.model.dto.WorkFlowStartResult;
import org.springframework.stereotype.Component;

/**
 * @author wenjs
 * @Description:
 * @date 2020/8/31 17:11
 */
@EventTypeSign("WORK_FLOW_START")
@Component
public class WorkFlowStartEventHandler extends AbstractEventHandler<WorkFlowStartEvent> {

    @Override
    protected EventResult preProcess(WorkFlowStartEvent event) {
        WorkFlowStartResult eventData  = new WorkFlowStartResult();



        return EventResult.build(eventData);
    }

    @Override
    protected EventResult postProcess(WorkFlowStartEvent event) {
        return null;
    }
}
