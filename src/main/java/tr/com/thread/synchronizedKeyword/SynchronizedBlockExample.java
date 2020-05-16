package tr.com.thread.synchronizedKeyword;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SynchronizedBlockExample {

    // Eger synchronized keyword'unu hic kullanmazsak kod arada bir calisir
    // ama cogunlukla hata yer. Bu hatayı aynı listeye
    // 2 thread aynı anda erisip islem yapmaya calisirken aliriz.
    // Asabilmek icin synchronized keywordunu koyariz.
    //                  HATA
    // Exception in thread "Thread­0" java.lang.ArrayIndexOutOfBoundsException: 15
    // at java.util.ArrayList.add(ArrayList.java:459)
    // at multithreading.chapter4.Application.addNewIntegerToList2(Application.java:74)
    // at multithreading.chapter4.Application.process(Application.java:55)
    // at multithreading.chapter4.Application.access$000(Application.java:7)
    //at multithreading.chapter4.Application$1.run(Application.java:31)
    // at java.lang.Thread.run(Thread.java:745)

    // synchronized kelimesi arka planda bir lock mekanizması kullanir. Bu lock
    // mekanizması da bir nesne icin yalnizca bir tanedir.
    // Yani burada list1 ve list2 objesi yerine method'lara synchronized keyword'unu
    // koysaydık. Methodlar bu sınıfın elemanı oldugu icin tamamen bu nesneye lock koyardik.
    // Yani addNewIntegerToList1 methoduna lock koyuldugu zaman addNewIntegerToList2 methoduna
    // da lock koyulmus olur. Boylece islem suremiz 2 katina cikmis olur.
    // O yuzden listelere yani farklı objelere lock koyarak hiz problemimizi cozmus olduk.


    private List<Integer> list1 = new ArrayList<>();
    private List<Integer> list2 = new ArrayList<>();

    public static void main(String[] args) {
        SynchronizedBlockExample synchronizedBlockExample = new SynchronizedBlockExample();
        long startTime = System.currentTimeMillis();
        synchronizedBlockExample.createThreadAndFillList();
        long endTime = System.currentTimeMillis();
        System.out.println("Geçen zaman : " + (endTime - startTime));
        System.out.println("List 1'in boyutu : " + synchronizedBlockExample.list1.size());
        System.out.println("List 2 'nin boyutu : " + synchronizedBlockExample.list2.size());
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

    private void addNewIntegerToList1() {
        synchronized (list1) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                System.out.println("List1 doldurulurken hata olustu");
            }
            list1.add(new Random().nextInt());
        }
    }

    private void addNewIntegerToList2() {
        synchronized (list2) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                System.out.println("List2 doldurulurken hata olustu");
            }
            list2.add(new Random().nextInt());
        }
    }

}
