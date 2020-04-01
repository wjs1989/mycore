package com.wjs.myProject.core.cache;

/**
 * @ClassName NullCacheKey
 * @Description: TODO
 * @Author wjs
 * @Date 2020/3/19
 * @Version V1.0
 **/
public final class NullCacheKey extends CacheKey {

    private static final long serialVersionUID = 3704229911977019465L;
    public NullCacheKey() {
        super();
    }

    @Override
    public void update(Object object) {
        throw new CacheException("Not allowed to update a NullCacheKey instance.");
    }

    @Override
    public void updateAll(Object[] objects) {
        throw new CacheException("Not allowed to update a NullCacheKey instance.");
    }
}
