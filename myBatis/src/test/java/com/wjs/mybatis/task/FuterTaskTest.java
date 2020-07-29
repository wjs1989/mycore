package com.wjs.mybatis.task;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author wenjs
 * @Description:
 * @date 2020/7/1 17:13
 */
public class FuterTaskTest {

    public void toDo() throws ExecutionException, InterruptedException {
        Callable<Integer> callable = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Thread.sleep(100);
                System.out.println("FuterTaskTest.call");
                return 10;
            }
        };

        FutureTask ft = new FutureTask(callable);

        new Thread(ft).start();
        System.out.println("FuterTaskTest.Thread");
        System.out.println(ft.get());
        System.out.println("FuterTaskTest.toDo");
    }

    public static void main(String[] args) {
        FuterTaskTest futerTaskTest= new FuterTaskTest();
        try {
            futerTaskTest.toDo();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
