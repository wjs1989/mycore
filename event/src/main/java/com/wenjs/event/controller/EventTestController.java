package com.wenjs.event.controller;

import com.alibaba.fastjson.JSONObject;
import com.wenjs.event.handler.annotition.EventHandler;
import com.wenjs.event.handler.model.EventDataManager;
import com.wenjs.event.handler.model.EventParams;
import com.wenjs.event.vo.Result;
import com.wenjs.event.vo.WorkFlowParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wenjs
 * @Description:
 * @date 2020/8/31 16:06
 */
@RestController
@RequestMapping("/event")
public class EventTestController {
    private Logger logger =  LoggerFactory.getLogger(EventTestController.class);


    @EventHandler
    @PostMapping("/post")
    public Result add(@RequestBody EventParams<WorkFlowParams> params){

        EventDataManager.getEventData("");

        logger.info(JSONObject.toJSONString(params));
        return Result.success();

        // 要不要启动工作流
    }



}
