package waiter;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class ThreadWaiter {

    public static void simpleWait() throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("First");
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Second");
            }
        });
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Third");
            }
        });
        t1.start();
        t1.join();

        t2.start();
        t2.join();

        t3.start();
        t3.join();
    }

    public static void waitCountDownLatch() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(3);
        new Thread(new Task("First thread", latch)).start();
        new Thread(new Task("Second thread", latch)).start();
        new Thread(new Task("Third thread", latch)).start();
        latch.await();
        System.out.println("All threads are up");
    }

    public static void waitCyclicBarrier() throws InterruptedException {
        CyclicBarrier barrier = new CyclicBarrier(3, new Runnable() {
            @Override
            public void run() {
                System.out.println("All parties are arrived at barrier, lets play");
            }
        });

        new Thread(new TaskBarier(barrier), "First").start();
        new Thread(new TaskBarier(barrier), "Second").start();
        new Thread(new TaskBarier(barrier), "Third").start();
    }

    private static class Task implements Runnable {

        private final String name;
        private final CountDownLatch latch;

        public Task(String name, CountDownLatch latch) {
            this.name = name;
            this.latch = latch;
        }

        @Override
        public void run() {
            System.out.println(name + " is Up");
            latch.countDown(); //reduce count of CountDownLatch by 1
        }
    }

    private static class TaskBarier implements Runnable {

        private final CyclicBarrier barrier;

        public TaskBarier(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + " is waiting on barrier");
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException ignored) {
            }
        }
    }
}
