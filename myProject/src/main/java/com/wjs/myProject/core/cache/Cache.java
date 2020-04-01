package com.wjs.myProject.core.cache;

import java.util.concurrent.locks.ReadWriteLock;

/**
 * @ClassName Cache
 * @Description: TODO
 * @Author wjs
 * @Date 2020/3/19
 * @Version V1.0
 **/
public interface Cache {

     /**
      * @Description: 缓存实现的id
      * @Param:
      * @Return:
     **/
    String getId();


    /**
     * @Description: 往缓存中添加数据
     * @Param:
     * @Return:
    **/
    void putObject(Object key,Object value);


  /**
   * @Description: 根据key获取缓存
   * @Param:
   * @Return: 
  **/
    Object getObject(Object key);

    /**
     * @Description: 根据key删除缓存
     * @Param:
     * @Return:
    **/
    Object removeObject(Object key);

    /**
     * @Description: 清理缓存
     * @Param:
     * @Return: 
    **/
    void clear();

    /**
     * @Description: 获取缓存个数
     * @Param:
     * @Return: 
    **/
    int getSize();

    /**
     * @Description: 获取读写锁
     * @Param:
     * @Return: 
    **/
    ReadWriteLock getReadWriteLock();
}
