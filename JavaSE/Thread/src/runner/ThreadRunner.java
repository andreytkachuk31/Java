package runner;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadRunner {

    private static final int COUNT_THREAD = 10;

    public static void simpleRun() {
        for (int i = 0; i < COUNT_THREAD; i++) {
            new Thread(new Task()).start();
        }
    }

    public static void runPool() {
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < COUNT_THREAD; i++) {
            threadPool.submit(new Task());
        }
        threadPool.shutdown();
    }

    private static class Task implements Runnable {
        @Override
        public void run() {
            System.out.println("Task performed by " + Thread.currentThread().getName());
        }
    }
}
