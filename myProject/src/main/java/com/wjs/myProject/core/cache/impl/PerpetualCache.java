package com.wjs.myProject.core.cache.impl;

import com.wjs.myProject.core.cache.Cache;
import com.wjs.myProject.core.cache.CacheException;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;

/**
 * @ClassName PerpetualCache
 * @Description: 缓存实现类型
 * @Author wjs
 * @Date 2020/3/19
 * @Version V1.0
 **/
public class PerpetualCache implements Cache {

    // 缓存id
    private final String id;

    //缓存存储容器
    private Map<Object,Object> cache = new HashMap<>();

    public PerpetualCache(String id) {
        this.id = id;
    }


    @Override
    public String getId() {
        return id;
    }

    @Override
    public void putObject(Object key, Object value) {
        cache.put(key,value);
    }

    @Override
    public Object getObject(Object key) {
        return cache.get(key);
    }

    @Override
    public Object removeObject(Object key) {
        return cache.remove(key);
    }

    @Override
    public void clear() {
        cache.clear();
    }

    @Override
    public int getSize() {
        return cache.size();
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return null;
    }

    /**
     * @Description: 判断二个缓存是否相同
     * @Param:
     * @Return: 
    **/
    @Override
    public boolean equals(Object o){
        if(getId() == null){
            throw new CacheException("缓存id不能为空");
        }

        if (this == o) {
            return true;
        }

        if (!(o instanceof Cache)) {
            return false;
        }

        Cache otherCache = (Cache) o;
        return getId().equals(otherCache.getId());
    }


}
