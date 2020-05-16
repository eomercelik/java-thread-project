package tr.com.thread.condition;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by asadsds on 29.03.2020.
 */
public class ConditionExample {

    public static void main(String[] args) throws InterruptedException {

        MyRunner myRunner = new MyRunner();
        Thread thread = new Thread(() -> {
            try {
                myRunner.firstThread();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                myRunner.secondThread();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        thread.start();
        thread2.start();

        thread.join();
        thread2.join();

        myRunner.printCount();
    }
}

class MyRunner {
    private int count = 0;

    private ReentrantLock reentrantLock = new ReentrantLock();

    private Condition condition = reentrantLock.newCondition();

    private void increment() {
        for (int i = 0; i < 10000; i++) {
            count++;
        }
    }

    public void firstThread() throws InterruptedException {
        reentrantLock.lock();
        System.out.println("firstThread methoduna girdi..");

        // thread bekletiliyor
        condition.await();
        System.out.println("firstThread method devam ediyor...");
        try {
            increment();
        } finally {
            System.out.println("firstThread method unclock oncesi");
            reentrantLock.unlock();
        }
        System.out.println("firstThread method bitti..");
    }

    public void secondThread() throws InterruptedException {
        Thread.sleep(2000);
        reentrantLock.lock();

        System.out.println("SecondThread methoduna girdi...");
        System.out.println("Devam etmek icin Enter tusuna basiniz");

        new Scanner(System.in).nextLine();

        // thread serbest birakiliyor. Ancak bu methodun isi bitmeden
        // thread tam olarak serbest birakilmis olmuyor. Bunu ciktidan farkedebilirsiniz.
        // signal methodu cagrisindan sonra bu methodun isi bitmeden firstThreadin
        // akmadigini farkedebilirsiniz.
        condition.signal();
        System.out.println("SecondThread devam ediyor");

        try {
            increment();
        } finally {
            System.out.println("SecondThread unlock oncesi");
            reentrantLock.unlock();
        }
        System.out.println("SecondThread bitti...");
    }


    public void printCount() {
        System.out.println("Count : " + count);
    }
}