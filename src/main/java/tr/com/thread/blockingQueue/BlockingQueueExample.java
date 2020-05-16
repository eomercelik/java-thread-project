package tr.com.thread.blockingQueue;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueExample {

    private static BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<Integer>(10);

    public static void main(String[] args) {
        Thread producerThread = new Thread(() -> {
            produceNumber();
        });

        Thread consumerThread = new Thread(() -> {
            consumeNumber();
        });

        producerThread.start();
        consumerThread.start();
        try {
            producerThread.join();
            consumerThread.join();
        } catch (InterruptedException e) {

        }
    }

    private static void produceNumber() {
        Random random = new Random();
        while (true) {
            try {
                blockingQueue.put(random.nextInt(100));
            } catch (InterruptedException e) {

            }
        }
    }

    private static void consumeNumber() {
        while (true) {
            try {
                Integer value = blockingQueue.take();
                System.out.println("Alınan sayi : " + value
                        + "\n Kuyrugun boyutu : " + blockingQueue.size());
            } catch (InterruptedException e) {

            }
        }
    }
}
