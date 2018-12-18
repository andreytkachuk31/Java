package semaphore;

import java.util.concurrent.Semaphore;

public class BinarySemaphore {

    private Semaphore binarySemaphore = new Semaphore(1);

    public void mutualExclusion() {
        try {
            binarySemaphore.acquire();

            System.out.println(Thread.currentThread().getName() + " inside mutual exclusive region");
            Thread.sleep(1000);
        } catch (InterruptedException ignore) {

        } finally {
            binarySemaphore.release();
            System.out.println(Thread.currentThread().getName() + " outside of mutual exclusive region");
        }
    }

    public static void main(String[] args) {
        final BinarySemaphore semaphore = new BinarySemaphore();

        new Thread() {
            @Override
            public void run() {
                semaphore.mutualExclusion();
            }
        }.start();

        new Thread() {
            @Override
            public void run() {
                semaphore.mutualExclusion();
            }
        }.start();
    }
}
