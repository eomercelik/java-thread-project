package tr.com.thread;

public class ExtendsThread {

    public static void main(String[] args) {
        MyRunner myRunner = new MyRunner("Omer");
        MyRunner myRunner2 = new MyRunner("Fatma");
        myRunner.start();
        myRunner2.start();
    }
}

class MyRunner extends Thread {

    private final String name;

    MyRunner(String name) {
        this.name = name;
    }
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Extends Thread " + name + " " + i);
        }
    }

}
