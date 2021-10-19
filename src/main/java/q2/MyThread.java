package q2;

import java.util.concurrent.CountDownLatch;

public class MyThread extends Thread {
    private boolean doPrint;
    private CountDownLatch latch;

    public MyThread(CountDownLatch latch) {
        this.doPrint = true;
        this.latch = latch;
    }

    @Override
    public void run() {
        while (doPrint) {
            System.out.println("Hello");
            try {
                Thread.sleep(100);
                latch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void interrupt() {
        System.out.println("Interrupted!!");
        doPrint = false;
    }
}
