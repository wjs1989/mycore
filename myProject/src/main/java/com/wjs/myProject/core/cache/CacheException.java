package com.wjs.myProject.core.cache;


/**
 * @ClassName 缓存异常类
 * @Description: TODO
 * @Author wjs
 * @Date 2020/3/19
 * @Version V1.0
 **/
public class CacheException extends RuntimeException {

    public CacheException() {
        super();
    }

    public CacheException(String message) {
        super(message);
    }

    public CacheException(String message, Throwable cause) {
        super(message, cause);
    }

    public CacheException(Throwable cause) {
        super(cause);
    }

}
