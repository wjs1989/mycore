package com.wjs.myProject.core.distributedlock.lock;

import org.springframework.data.redis.core.RedisTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author wenjs
 * @Description:
 * @date 2020/7/29 16:12
 */
public class ReentrantRedisLock implements Lock, java.io.Serializable{
    private static final long serialVersionUID = 7373984872572414699L;

    private final ReentrantRedisLock.Sync sync;

    abstract static class Sync extends AbstractQueuedSynchronizerRedis {

        private static final long serialVersionUID = -5179523762034025860L;
        public final Map<Thread,String> threadIdCache = new HashMap<>();

        abstract void lock();

        final boolean nonfairTryAcquire(int acquires) {
            final Thread current = Thread.currentThread();

            int c = getState();
            if (c == 0) {
                if (compareAndSetState(0, acquires)) {
                    setExclusiveOwnerThread(current);
                    return true;
                }
            }
            else if (current == getExclusiveOwnerThread()) {
                int nextc = c + acquires;
                if (nextc < 0){ // overflow
                    throw new Error("Maximum lock count exceeded");
                }
                setState(nextc);
                return true;
            }
            return false;
        }

        @Override
        protected final boolean tryRelease(int releases) {
            while (!tryRedisRelease(releases)) {
            }

            int c = getState() - releases;
            if (Thread.currentThread() != getExclusiveOwnerThread())
                throw new IllegalMonitorStateException();
            boolean free = false;
            if (c == 0) {
                free = true;
                setExclusiveOwnerThread(null);
            }
            setState(c);
            return free;
        }

        protected  final boolean tryRedisRelease(int releases){
            if (Thread.currentThread() != getExclusiveOwnerThread()) {
                throw new IllegalMonitorStateException();
            }

            int old = getRedisState();
            int c = old - releases;
            boolean free = true;
            while(!compareAndSetRedisState(old,c,threadIdCache.get(Thread.currentThread())));
            if (c == 0) {
                threadIdCache.remove(Thread.currentThread());
            }
            return free;
        }

        @Override
        protected final boolean isHeldExclusively() {
            return getExclusiveOwnerThread() == Thread.currentThread();
        }

        final ConditionObject newCondition() {
            return new ConditionObject();
        }


        final Thread getOwner() {
            return getState() == 0 ? null : getExclusiveOwnerThread();
        }

        final int getHoldCount() {
            return isHeldExclusively() ? getState() : 0;
        }

        final boolean isLocked() {
            return getState() != 0;
        }

        private void readObject(java.io.ObjectInputStream s)
                throws java.io.IOException, ClassNotFoundException {
            s.defaultReadObject();
            setState(0); // reset to unlocked state
        }

        final boolean nonfairRedisTryAcquire(int acquires) {
            int c = getRedisState();
            String threadId = threadIdCache.get(Thread.currentThread());
            if (c == 0) {
                if (compareAndSetRedisState(0, acquires,threadId)) {
                    return true;
                }
            } else if (Objects.equals(threadId, getThreadId())) {
                int nextc = c + acquires;
                if (nextc < 0) { // overflow
                    throw new Error("Maximum redis lock count exceeded");
                }
                setRedisState(nextc);
                return true;
            }
            return false;
        }
    }

    /**
     * 非公平锁
     */
    static final class NonfairSync extends ReentrantRedisLock.Sync {
        private static final long serialVersionUID = 7316153563782823691L;

        String redisKey ;
        RedisTemplate redisTemplate;

        public NonfairSync(String redisKey,RedisTemplate redisTemplate) {
            super();
            this.redisKey = redisKey;
            this.redisTemplate = redisTemplate;
        }

        @Override
        final void lock() {
            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
            }else {
                acquire(1);
            }

            //执行到这里，说明获得了本地锁
            //在去获取redis锁
            lockRedis();
        }

        void lockRedis(){
            setRedisKey(redisKey);
            setRedisTemplate(redisTemplate);
            while (!nonfairRedisTryAcquire(1)){
                // 获取redis锁
            }
        }

        @Override
        protected final boolean tryAcquire(int acquires) {
            return nonfairTryAcquire(acquires);
        }

    }



    public ReentrantRedisLock(String key,RedisTemplate redisTemplate){
        sync = new NonfairSync(key,redisTemplate);
    }

    @Override
    public void lock() {
        setThreadId();
        sync.lock();
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        setThreadId();
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        setThreadId();
        return sync.nonfairTryAcquire(1);
    }

    @Override
    public boolean tryLock(long timeout, TimeUnit unit) throws InterruptedException {
        setThreadId();
        return sync.tryAcquireNanos(1, unit.toNanos(timeout));
    }

    @Override
    public void unlock() {
        sync.release(1);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }

    private void setThreadId(){
        String threadId = "lock_thread_"+ UUID.randomUUID().toString().replace("-","");
        System.out.println("当前线程id："+threadId);
        sync.threadIdCache.putIfAbsent(Thread.currentThread(),threadId);
    }
}
