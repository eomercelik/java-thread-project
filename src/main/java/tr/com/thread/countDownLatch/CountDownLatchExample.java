package tr.com.thread.countDownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchExample {

    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 3; i++) {
            executorService.submit(new Thread(new MyRunnable(countDownLatch)));
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
        }
        System.out.println("Program bitti.");

    }
}
class MyRunnable implements Runnable {

    private CountDownLatch countDownLatch;

    public MyRunnable(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        System.out.println("Runnable basliyor ");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {

        }
        countDownLatch.countDown();
    }
}