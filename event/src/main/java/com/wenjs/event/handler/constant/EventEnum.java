package com.wenjs.event.handler.constant;

/**
 * @author wenjs
 * @Description:
 * @date 2020/9/1 15:56
 */
public enum EventEnum {

    WORK_FLOW_START("工作流启动事件","WORK_FLOW_START");

    ;
    private String name;
    private String code;

    EventEnum(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }
}
