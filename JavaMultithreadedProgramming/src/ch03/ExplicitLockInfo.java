package ch03;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: withlzc
 * @Description:
 * @Date: Created in 2019-01-27 15:27
 */
public class ExplicitLockInfo {

    private static final Lock lock = new ReentrantLock();
    private static int shareData = 0;

    public static void main(String[] args) throws Exception{
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    try {
                        Thread.sleep(2200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    shareData = 1;
                } finally {
                    lock.unlock();
                }
            }
        });
        t.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock.lock();
        try {
            System.out.println("shareData: " + shareData);
        } finally {
            lock.unlock();
        }

    }
}
