package com.wenjs.event.handler.model;

import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wenjs
 * @Description:
 * @date 2020/8/31 16:52
 */
@Data
public class EventParams<T> {
    private List<HashMap<String,Object>> events;
    private T params;
}
