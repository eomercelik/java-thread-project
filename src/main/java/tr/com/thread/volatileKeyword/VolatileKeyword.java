package tr.com.thread.volatileKeyword;

public class VolatileKeyword {

    public static void main(String[] args) throws InterruptedException {
        MyProcessor myProcessor = new MyProcessor();
        myProcessor.start();

        Thread.sleep(10000);
        System.out.println("------------------------------------------------------------");
        myProcessor.shutDown();
    }
}

class MyProcessor extends Thread {

    // bu degiskeni volatile olarak tanimlamak daha dogru olacaktir. Cunku derleyici optimizasyon
    // icin bu degiskeni cache atip her seferinde cache'den getirebilir. Biz bu degeri degistirsekte
    // ilgili memory adresi yerine cache baktigi icin kod dogru calismayabilir.
    // private boolean isRunning = true;
    private volatile boolean isRunning = true;

    @Override
    public void run() {
        while (isRunning) {
            System.out.println("Merhaba");
        }
    }

    public void shutDown() {
        this.isRunning = false;
    }
}
