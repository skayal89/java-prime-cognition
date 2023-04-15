package concurrency;

import java.util.Random;


public class SimpleThreadTask extends Thread {
    public void run(){
        long startTime = System.currentTimeMillis();
        Random random = new Random();
        int times = 100000;
        while(times-- > 0){
            int rand = random.nextInt(500-1) + 1;
            System.out.println(getName() + "thread is running. Rand - "+rand);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
