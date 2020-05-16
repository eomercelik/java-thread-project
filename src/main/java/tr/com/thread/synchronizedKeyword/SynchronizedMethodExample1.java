package tr.com.thread.synchronizedKeyword;

public class SynchronizedMethodExample1 {


    private int count = 0;

    public static void main(String[] args) throws InterruptedException {
        SynchronizedMethodExample1 synchronizedMethodExample1 = new SynchronizedMethodExample1();
        synchronizedMethodExample1.processCount();
    }

    // synchronized keywordu olmasaydi 2 thread ayni anda bu methoda erisebilirdi.
    // Ve count degeri 20000 yerine daha az degerler cikardi.
    // synchronized keywordunu kaldirarak deneyebilirsiniz.
    private synchronized void setCount() {
        count++;
    }

    private void processCount() throws InterruptedException {

        Thread thread1 = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    setCount();
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 10000; i++) {
                    setCount();
                }
            }
        });

        thread1.start();
        thread2.start();

        // Join methodlarini yazmasaydim daha thread'lerin isi bitmeden proje kapanir.
        // Bu yuzden main thread ilk once thread1.join() ile bekletiliyor
        // daha sonra main thread thread2.join() ile bekletiliyor.
        // Buradaki bekleme islemi yanlis anlasilmasin.
        // thread1 ve thread2 aynı anda calisiyor. Birbirlerini bekletmiyorlar.
        // join methody sadece current thread'i(main thread) bekletmiş oluyor.
        thread1.join();
        thread2.join();
        System.out.println(count);
    }
}
