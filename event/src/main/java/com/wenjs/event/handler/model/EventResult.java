package com.wenjs.event.handler.model;

import lombok.Data;

import java.util.HashMap;

/**
 * @author wenjs
 * @Description:
 * @date 2020/9/1 14:13
 */
@Data
public class EventResult {
    public EventData result;

    public EventResult(EventData result) {
        this.result = result;
    }

    public static EventResult build(EventData eventData){
      return new EventResult(eventData);
    }
}
