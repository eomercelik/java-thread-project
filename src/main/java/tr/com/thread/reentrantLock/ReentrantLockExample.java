package tr.com.thread.reentrantLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by asadsds on 29.03.2020.
 */
public class ReentrantLockExample {


    public static void main(String[] args) throws InterruptedException {
        MyRunnable myRunnable = new MyRunnable();

        Thread thread = new Thread(() -> {
            myRunnable.firstThread();
        });

        Thread thread2 = new Thread(() -> {
            myRunnable.secondThread();
        });

        thread.start();
        thread2.start();

        thread.join();
        thread2.join();

        myRunnable.printCount();
    }
}

class MyRunnable {

    private int count = 0;
    private Lock lock = new ReentrantLock();

    private void increment() {
        for (int i = 0; i < 10000; i++) {
            count++;
        }
    }

    // synchronized bir block haline geldi
    public void firstThread() {
        lock.lock();
        try {
            increment();
        } finally {
            lock.unlock();
        }
    }

    // synchronized bir block haline geldi
    public void secondThread() {
        lock.lock();
        try {
            increment();
        } finally {
            lock.unlock();
        }
    }

    public void printCount() {
        System.out.println("Count : " + count);
    }
}
