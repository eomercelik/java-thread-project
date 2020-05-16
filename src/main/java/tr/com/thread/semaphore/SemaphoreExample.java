package tr.com.thread.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by asadsds on 29.03.2020.
 */
public class SemaphoreExample {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 15; i++) {
            executorService.submit(() -> {
                try {
                    Connection.getInstance().connect();
                } catch (InterruptedException e) {
                }
            });
        }
        executorService.shutdown();
    }

}

class Connection {
    private static Connection instance = new Connection();
    private int connectionCount = 0;
    private Semaphore semaphore = new Semaphore(3);

    private Connection() {
    }

    public static Connection getInstance() {
        return instance;
    }

    public void connect() throws InterruptedException {
        semaphore.acquire();
        try {
            System.out.println(Thread.currentThread().hashCode());
            doConnect();
        } finally {
            semaphore.release();
        }
    }

    private void doConnect() throws InterruptedException {
        synchronized (this) {
            connectionCount++;
            System.out.println("Bağlantısayısı:" + connectionCount);
        }
        Thread.sleep(2000);
        synchronized (this) {
            connectionCount--;
        }
    }
}
