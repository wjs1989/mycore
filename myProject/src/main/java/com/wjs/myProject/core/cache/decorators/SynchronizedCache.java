package com.wjs.myProject.core.cache.decorators;

import com.wjs.myProject.core.cache.Cache;

import java.util.concurrent.locks.ReadWriteLock;

/**
 * @ClassName SynchronizedCache
 * @Description: 装饰器，使得缓存有同步功能
 * @Author wjs
 * @Date 2020/3/19
 * @Version V1.0
 **/
public class SynchronizedCache implements Cache {
    //缓存代理对象
    private final Cache delegate;

    public SynchronizedCache(Cache delegate) {
        this.delegate = delegate;
    }

    @Override
    public String getId() {
        return delegate.getId();
    }

    @Override
    public synchronized void putObject(Object key, Object object) {
        delegate.putObject(key, object);
    }

    @Override
    public synchronized Object getObject(Object key) {
        return delegate.getObject(key);
    }

    @Override
    public synchronized Object removeObject(Object key) {
        return delegate.removeObject(key);
    }

    @Override
    public synchronized void clear() {
        delegate.clear();
    }

    @Override
    public synchronized int getSize() {
        return delegate.getSize();
    }

    @Override
    public int hashCode() {
        return delegate.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return delegate.equals(obj);
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return null;
    }
}
