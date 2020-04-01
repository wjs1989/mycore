package com.wjs.myProject.core.cache;

import com.wjs.myProject.core.cache.decorators.LoggingCache;
import com.wjs.myProject.core.cache.decorators.SynchronizedCache;
import com.wjs.myProject.core.cache.impl.PerpetualCache;
import org.junit.Test;

/**
 * @ClassName CacheTest
 * @Description: TODO
 * @Author wjs
 * @Date 2020/3/19
 * @Version V1.0
 **/
public class CacheTest {

    @Test
    public void doTest(){
        Cache cache = new SynchronizedCache(new LoggingCache(new PerpetualCache("cache")));

        cache.putObject("name","wjs");
        cache.putObject("age",18);


        System.out.print(cache.getObject("name"));
        System.out.print( cache.getObject("age"));

    }



    @Test
    public void doCacheKey(){
        CacheKey key1 = new CacheKey();
        key1.update("name");
        key1.update("wjs");
        key1.update("age");
        key1.update(18);

        Cache cache = new SynchronizedCache(new LoggingCache(new PerpetualCache("cache")));

        cache.putObject(key1,"{\"name\":\"wjs\",\"age\":18}");

        System.out.print(cache.getObject(key1));
    }
}
