package tr.com.thread;

public class ImplementsRunnable {

    public static void main(String[] args) {
        Thread thread = new Thread(new MyRunnable("Omer"));
        Thread thread2 = new Thread(new MyRunnable("Fatma"));
        thread.start();
        thread2.start();

        // Eğer Runnable 'i bir kere kullanacaksak anonymous class
        // kullanmak daha mantikli olacaktir
        Thread thread3 = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println("Log " + " " + i);
                }
            }
        });
    }
}

class MyRunnable implements Runnable {

    private final String name;

    MyRunnable(String name) {
        this.name = name;
    }

    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Runnable " + name + " " + i);
        }
    }
}
