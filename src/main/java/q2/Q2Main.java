package q2;

import java.util.concurrent.CountDownLatch;

public class Q2Main {
    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(20);
        Thread backgroundThread = new MyThread(latch);
        backgroundThread.start();

        try {
            latch.await();
            backgroundThread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
