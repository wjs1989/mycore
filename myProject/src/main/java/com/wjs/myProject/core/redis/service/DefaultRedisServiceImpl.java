package com.wjs.myProject.core.redis.service;

import com.wjs.myProject.core.redis.config.RedisKeyEnum;
import com.wjs.myProject.core.redis.model.BaseRedis;
import com.wjs.myProject.core.redis.model.RedisResult;
import com.wjs.myProject.core.redis.model.StringRedis;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author wenjs
 * @Description:
 * @date 2020/8/12 11:59
 */
@Service
public class DefaultRedisServiceImpl implements RedisService, InitializingBean {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    private Map<DataType, Consumer<? extends BaseRedis>> typeSaveCache = new HashMap<>();
    private Map<DataType, Function<String,RedisResult>> typeGetCache = new HashMap<>();


    @Override
    public void save(BaseRedis request){
        getSaveConsumer(request.getDataType()).accept(request);
    }

    @Override
    public RedisResult get(RedisKeyEnum redisKey){
        return getQueryConsumer(redisKey.getDataType()).apply(redisKey.getKey());
    }

    private RedisResult getString(String key){
        RedisResult redisResult = new RedisResult();

        Object object = redisTemplate.opsForValue().get(key);
        StringRedis stringRedis = new StringRedis();
        stringRedis.setValue(String.valueOf(object));

        redisResult.setString(stringRedis);
        return redisResult;
    }

    private void saveString(BaseRedis request){
        StringRedis srr = (StringRedis)request;
        redisTemplate.opsForValue().set(srr.getKey(),srr.getValue(), Duration.ofSeconds(request.getExpire()));
    }

    private Consumer getSaveConsumer(DataType dataType){
        Consumer<? extends BaseRedis> consumer = typeSaveCache.get(dataType);
        Objects.requireNonNull(consumer);

        return consumer;
    }

    private Function<String,RedisResult> getQueryConsumer(DataType  dataType){
        Function<String,RedisResult> queryFunction = typeGetCache.get(dataType);
        Objects.requireNonNull(queryFunction);

        return queryFunction;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        typeSaveCache.put(DataType.STRING,this::saveString);


        typeGetCache.put(DataType.STRING,this::getString);
    }
}
