package com.wjs.mybatis.task;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @author wenjs
 * @Description:
 * @date 2020/7/1 17:26
 */
public class ForkJoin extends RecursiveTask<Integer> {
    Integer start;
    Integer end;
    int critical = 1000;

    public ForkJoin( Integer start, Integer end){
        this. start = start;
        this. end = end;
    }

    @Override
    protected Integer compute() {
        int m = (end - start)/2 + start;
        if((end - start)<=critical){
            int sum = 0;
            for (int i = start ;i<=end;i++){
                sum +=i;
            }
            System.out.println(Thread.currentThread().getName()+"," +start +"," + end + "," + sum);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return sum;
        }else{
            ForkJoin l = new ForkJoin(start,m);
            ForkJoin r = new ForkJoin(m+1,end);
            l.fork();
            r.fork();
            try {
                return l.join()+r.join();
            } catch (Exception e) {
                return 0;
            }
        }
    }


    public static void main(String[] args) {


        ForkJoin l = new ForkJoin(1,4000);
        ForkJoinPool forkJoinPool = new ForkJoinPool();//实现ForkJoin 就必须有ForkJoinPool的支持
        Integer invoke = forkJoinPool.invoke(l);  //invoke 同步  submit 异步
        System.out.println(invoke);

    }
}
