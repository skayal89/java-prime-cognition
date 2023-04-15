package concurrency;

public class ThreadTaskMain {
    public static void main(String[] args) {
        new SimpleThreadTask().start();
        new Thread(new SimpleRunnableTask()).start();
    }
}
