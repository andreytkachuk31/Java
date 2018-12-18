package producer_consumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class ProducerConsumerPattern {

    public static void run() {
        BlockingQueue sharedQueue = new LinkedBlockingDeque();

        Thread prodThread = new Thread(new Producer(sharedQueue));
        Thread consThread = new Thread(new Consumer(sharedQueue));

        prodThread.start();
        consThread.start();
    }
}
