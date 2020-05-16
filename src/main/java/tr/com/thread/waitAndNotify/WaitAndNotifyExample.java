package tr.com.thread.waitAndNotify;

import java.util.Scanner;

/**
 * Created by asadsds on 28.03.2020.
 */
public class WaitAndNotifyExample {

    public static void main(String[] args) throws InterruptedException {
        final MyProcessor myProcessor = new MyProcessor();

        Thread producerThread = new Thread(() -> {
            try {
                myProcessor.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread consumerThread = new Thread(() -> {
            try {
                myProcessor.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        producerThread.start();
        consumerThread.start();


        producerThread.join();
        consumerThread.join();

    }
}

class MyProcessor {

    public void produce() throws InterruptedException {
        synchronized (this) {
            System.out.println("Produce method girdi..");
            // thread ' i bekletir ve synchronized blogun kilidini acar.
            // yani baska bir thread girebilir o thread beklerken
            wait();
            System.out.println("Produce method bitti..");
        }
    }


    public void consume() throws InterruptedException {
        Thread.sleep(2000);
        Scanner scanner = new Scanner(System.in);
        synchronized (this) {
            System.out.println("Consume method girdi..");
            System.out.println("Devam etmek için Enter'a basınız : ");
            scanner.nextLine();
            System.out.println("Consume method devam ediyor..");
            // bekleyen thread'i devam etmesi icin serbest birakir
            // notify cagrildigi an serbes birakilmaz bloktaki islemlerin
            // tamamlanmasi beklenir thread oyle calisabilir.
            notify();
            Thread.sleep(5000);
            System.out.println("Consume method bitti..");
        }
    }
}
