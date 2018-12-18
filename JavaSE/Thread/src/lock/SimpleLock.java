package lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SimpleLock {

    private final Lock lock;
    private String data;

    public SimpleLock(Lock lock) {
        this.lock = lock;
    }

    public void initData() {
        this.lock.lock();
        try {
            if (this.data == null) {
                System.out.println("Test");
                this.data = "init";
            }
        } finally {
            this.lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final SimpleLock simpleLock = new SimpleLock(new ReentrantLock());

        Runnable task = new Runnable() {
            @Override
            public void run() {
                simpleLock.initData();
            }
        };

        for (int i = 0; i < 10; i++) {
            new Thread(task).start();
        }
    }
}
