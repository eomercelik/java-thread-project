package tr.com.thread.executorService;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceExample {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        ArrayList<String> nameList = new ArrayList<String>() {{
            add("Runnable 1 ");
            add("Runnable 2");
            add("Runnable 3");
            add("Runnable 4");
            add("Runnable 5");
            add("Runnable 6");
            add("Runnable 7");
            add("Runnable 8");
            add("Runnable 9");
            add("Runnable 10");
        }};

        for (String name : nameList) {
            executorService.submit(new MyRunnable(name));
        }
        System.out.println("HELLOOO1");
        executorService.shutdown();
        System.out.println("HELLOOO2");
        executorService.awaitTermination(1, TimeUnit.DAYS);
        System.out.println("HELLOOO3");
    }
}

class MyRunnable implements Runnable {

    private String name;

    public MyRunnable(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().hashCode());
        System.out.println(name + " adli Runnable basliyor ");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {

        }
        System.out.println(name + " adli Runnable sonladi");
    }
}
