package ch05;

import util.Tools;
import util.Debug;
import java.util.concurrent.CountDownLatch;

/**
 * @Author: withlzc
 * @Description:
 * @Date: Created in 2019-02-11 16:08
 */
public class CountDownLatchExample {

    private static final CountDownLatch latch = new CountDownLatch(4);

    private static int data;

    public static void main(String[] args) throws InterruptedException {
        Thread workerThread = new Thread(){
            @Override
            public void run() {
                for (int i = 1; i < 10; i++) {
                    data = i;
                    latch.countDown();
                    Tools.randomPause(1000);

                }
            };
        };

        workerThread.start();
        latch.await();
        Debug.info("It's done. data = %d", data);

    }
}
