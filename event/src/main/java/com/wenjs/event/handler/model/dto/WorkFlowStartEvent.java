package com.wenjs.event.handler.model.dto;

import com.wenjs.event.handler.model.Event;
import lombok.Data;

/**
 * @author wenjs
 * @Description: 工作流启动事件模型
 * @date 2020/9/1 10:47
 */
@Data
public class WorkFlowStartEvent extends Event {
    /**
     * 业务key
     */
    private String businessKey;

    /**
     * 流程定义key
     */
    private String processDefinitionKey;
}
