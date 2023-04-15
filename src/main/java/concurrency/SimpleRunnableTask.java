package concurrency;

import java.util.Random;

public class SimpleRunnableTask implements Runnable {
    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        Random random = new Random();
        int times = 100000;
        while(times-- > 0){
            int rand = random.nextInt(10000 - 1000) + 1000;
            System.out.println(Thread.currentThread().getName() + "thread is running. Rand - "+rand);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
