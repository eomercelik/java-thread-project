package tr.com.thread.synchronizedKeyword;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SynchronizedMethodExampleAdvanced {

    private List<Integer> list1 = new ArrayList<>();
    private List<Integer> list2 = new ArrayList<>();

    public static void main(String[] args) {
        SynchronizedMethodExampleAdvanced synchronizedMethodExampleAdvanced = new SynchronizedMethodExampleAdvanced();
        long startTime = System.currentTimeMillis();
        synchronizedMethodExampleAdvanced.createThreadAndFillList();
        long endTime = System.currentTimeMillis();
        System.out.println("Geçen zaman : " + (endTime - startTime));
        System.out.println("List 1'in boyutu : " + synchronizedMethodExampleAdvanced.list1.size());
        System.out.println("List 2 'nin boyutu : " + synchronizedMethodExampleAdvanced.list2.size());
    }

    private void createThreadAndFillList() {
        Thread thread1 = new Thread(() -> {
            fillList1AndList2();
        });


        Thread thread2 = new Thread(() -> {
            fillList1AndList2();
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private void fillList1AndList2() {
        for (int i = 0; i < 10; i++) {
            addNewIntegerToList1();
            addNewIntegerToList2();
        }
    }

    private synchronized void addNewIntegerToList1() {
        //  synchronized (this) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                System.out.println("List1 doldurulurken hata olustu");
            }
            list1.add(new Random().nextInt());
        //  }
    }

    // Methoda synchronized yazma ile synchronized(this) yapmak aynı sey.
    // 2 side bu nesneye lock koyuyor.
    private synchronized void addNewIntegerToList2() {
       // synchronized (this) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                System.out.println("List2 doldurulurken hata olustu");
            }
            list2.add(new Random().nextInt());
       // }
    }

}
