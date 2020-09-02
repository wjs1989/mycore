package com.wenjs.event.handler.model;

import lombok.Data;

/**
 * @author wenjs
 * @Description:
 * @date 2020/8/31 16:53
 */
@Data
public class Event {

    /**
     * 事件类型 PREEVENT 前置，POSTEVENT 后置
     */
    private String type;
    private String name;
}
