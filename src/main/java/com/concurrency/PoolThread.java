package com.concurrency;

import java.util.concurrent.*;

public class PoolThread {

    public void fixedPool(int numberOfThread) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThread);
        Future f1 = executorService.submit(new SimpleRunnableTask());
        Future<Integer> f2 = executorService.submit(getSampleCallable());
        executorService.submit(getSampleRunnable());
        Future<Integer> f3 = executorService.submit(getSampleCallable());

        System.out.println(f2.get());
        System.out.println(f3.get());
        System.out.println(f1.get());
        executorService.shutdown();
    }

    public Runnable getSampleRunnable(){
        Runnable runnable = () -> {
            long startTime = System.currentTimeMillis();
            int i = 1;
            int times = 10;
            System.out.println(Thread.currentThread().getName() + " start time "+ startTime);
            while(times-- > 0){
                System.out.println(Thread.currentThread().getName() + " runnable thread is running. Count - "+i++);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        return runnable;
    }

    public Callable<Integer> getSampleCallable(){
        Callable<Integer> callable = () -> {
            long startTime = System.currentTimeMillis();
            int i = 1;
            int times = 10;
            System.out.println(Thread.currentThread().getName() + " start time "+ startTime);
            while(times-- > 0){
                i += i;
                System.out.println(Thread.currentThread().getName() + " callable thread is running. Count - "+i++);
                try {
                    TimeUnit.MILLISECONDS.sleep(90);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            return i;
        };
        return callable;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        new PoolThread().fixedPool(3);
    }
}
