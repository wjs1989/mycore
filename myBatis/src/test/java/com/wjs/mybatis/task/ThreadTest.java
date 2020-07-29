package com.wjs.mybatis.task;

import com.wjs.mybatis.model.ZeroEvenOdd;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wenjs
 * @Description:
 * @date 2020/6/29 15:35
 */
public class ThreadTest {

    @Test
    public void ThreadTest() throws InterruptedException {

        ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(5);
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    zeroEvenOdd.odd(i -> {
                        System.out.print(i);
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread1.setName("odd");
        thread1.start();

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    zeroEvenOdd.even(i -> {
                        System.out.print(i);
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread2.setName("even");
        //   thread2.start();

        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                    zeroEvenOdd.zero(i -> {
                        System.out.print(i);
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread3.setName("zero");
        thread3.start();

        Thread.sleep(50000000);
    }

    @Test
    public void ThreadTest1() throws InterruptedException {
        Lock lock = new ReentrantLock();
        Condition zero = lock.newCondition();

        new Thread(new Runnable() {
            @Override
            public void run() {

                lock.lock();
                try {
                    System.out.println("ThreadTest.runq");
                    zero.await();
                    System.out.println("ThreadTest.runa");
                    zero.await();
                    System.out.println("ThreadTest.runb");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }).start();

for(int i =0; i <2;i++){

        try {
            Thread.sleep(10000);lock.lock();
            System.out.println("ThreadTest.runq1"+i);
            zero.signal();
            System.out.println("ThreadTest.runa2"+i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    ConcurrentHashMap
}
        try {
            Thread.sleep(100001000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
