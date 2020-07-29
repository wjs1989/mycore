package com.wjs.mybatis.model;

import sun.reflect.misc.MethodUtil;
import sun.reflect.misc.ReflectUtil;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

/**
 * @author wenjs
 * @Description:
 * @date 2020/6/29 15:14
 */
public class ZeroEvenOdd {
    Lock lock = new ReentrantLock();
    private Condition zero = lock.newCondition();
    private Condition even = lock.newCondition();
    private Condition odd = lock.newCondition();
    private int n;
    private int i = 1 ;
    private Object object = new Object();
    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        lock.lock();
        try {

            while (i < n) {
                printNumber.accept(0);
                i++;
                if (i % 2 == 0) {
              //      even.signal();
                } else {
                    odd.signal();
                }
                zero.await();
            }
        } finally {
            lock.unlock();
        }

    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        lock.lock();
        try {
            if(i<1){
                even.await();
            }
            while (i <= n) {
                if(i % 2 == 0){
                    printNumber.accept(i);
                }

                zero.signal();
                even.await();
            }
        } finally {
            lock.unlock();
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        lock.lock();
        try {
            if(i<1){
                odd.await();
            }
            while (i <= n) {
                if(i % 2 != 0){
                    printNumber.accept(i);
                }
                zero.signal();
                odd.await();

            }
        } finally {
            lock.unlock();
        }
    }
}
