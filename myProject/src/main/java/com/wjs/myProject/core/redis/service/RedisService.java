package com.wjs.myProject.core.redis.service;

import com.wjs.myProject.core.redis.config.RedisKeyEnum;
import com.wjs.myProject.core.redis.model.BaseRedis;
import com.wjs.myProject.core.redis.model.RedisResult;

/**
 * @author wenjs
 * @Description:
 * @date 2020/8/12 11:59
 */
public interface RedisService {
    void save(BaseRedis request);
    RedisResult get(RedisKeyEnum redisKey);
}
