package concurrency;

import java.util.Random;

public class SimpleRunnableTask implements Runnable {
    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        Random random = new Random();
        int times = 10;
        System.out.println(Thread.currentThread().getName() + " start time "+ startTime);
        while(times-- > 0){
            int rand = random.nextInt(100 - 1) + 1;
            System.out.println(Thread.currentThread().getName() + " thread is running. Rand - "+rand);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
