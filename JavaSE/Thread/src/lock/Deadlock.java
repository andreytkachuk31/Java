package lock;

public class Deadlock {

    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    // Need to add Thread#sleep in first synchronized block to get deadlock
    public void method1() {
        synchronized (this.lock1) {
            synchronized (this.lock2) {
                System.out.println("Aquired lock on lock1");
            }
        }
    }

    public void method2() {
        synchronized (this.lock2) {
            synchronized (this.lock1) {
                System.out.println("Aquired lock on lock2");
            }
        }
    }

    public static void main(String[] args) {
        final Deadlock deadlock = new Deadlock();

        new Thread(new Runnable() {
            @Override
            public void run() {
                deadlock.method1();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                deadlock.method2();
            }
        }).start();
    }
}
