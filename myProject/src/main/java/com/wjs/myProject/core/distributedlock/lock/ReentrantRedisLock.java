package com.wjs.myProject.core.distributedlock.lock;

import org.springframework.data.redis.core.RedisTemplate;

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

        /**
         * Performs {@link Lock#lock}. The main reason for subclassing
         * is to allow fast path for nonfair version.
         */
        abstract void lock();

        /**
         * Performs non-fair tryLock.  tryAcquire is implemented in
         * subclasses, but both need nonfair try for trylock method.
         */
        final boolean nonfairTryAcquire(int acquires) {
            final Thread current = Thread.currentThread();
            int c = getRedisState();
            if (c == 0) {
                if (compareAndSetRedisState(0, acquires)) {
                    setExclusiveOwnerThread(current);
                    return true;
                }
            }
            else if (current == getExclusiveOwnerThread()) {
                int nextc = c + acquires;
                if (nextc < 0) { // overflow
                    throw new Error("Maximum lock count exceeded");
                }
                setRedisState(nextc);
                return true;
            }
            return false;
        }

        @Override
        protected final boolean tryRelease(int releases) {
            int c = getRedisState() - releases;
            if (Thread.currentThread() != getExclusiveOwnerThread()) {
                throw new IllegalMonitorStateException();
            }
            boolean free = false;
            if (c == 0) {
                free = true;
                setExclusiveOwnerThread(null);
                setRedisState(c);
                deleteRedisState();
            }else{
                setRedisState(c);
            }
            return free;
        }

        @Override
        protected final boolean isHeldExclusively() {
            // While we must in general read state before owner,
            // we don't need to do so to check if current thread is owner
            return getExclusiveOwnerThread() == Thread.currentThread();
        }

        final ConditionObject newCondition() {
            return new ConditionObject();
        }

        // Methods relayed from outer class

        final Thread getOwner() {
            return getRedisState() == 0 ? null : getExclusiveOwnerThread();
        }

        final int getHoldCount() {
            return isHeldExclusively() ? getRedisState() : 0;
        }

        final boolean isLocked() {
            return getRedisState() != 0;
        }

        /**
         * Reconstitutes the instance from a stream (that is, deserializes it).
         */
        private void readObject(java.io.ObjectInputStream s)
                throws java.io.IOException, ClassNotFoundException {
            s.defaultReadObject();
            setRedisState(0); // reset to unlocked state
        }

    }

    /**
     * 非公平锁
     */
    static final class NonfairSync extends ReentrantRedisLock.Sync {
        private static final long serialVersionUID = 7316153563782823691L;

        public NonfairSync(String key,RedisTemplate redisTemplate) {
            super();
            setRedisKey(key);
            setRedisTemplate(redisTemplate);
            setRedisState(0);
        }

        /**
         * Performs lock.  Try immediate barge, backing up to normal
         * acquire on failure.
         */
        @Override
        final void lock() {
            if (compareAndSetRedisState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
            }else {
                acquire(1);
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
        sync.lock();
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.nonfairTryAcquire(1);
    }

    @Override
    public boolean tryLock(long timeout, TimeUnit unit) throws InterruptedException {
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
}
