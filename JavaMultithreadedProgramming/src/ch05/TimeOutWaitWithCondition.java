package ch05;

import util.Tools;
import util.Debug;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: withlzc
 * @Description:
 * @Date: Created in 2019-02-06 15:19
 */
public class TimeOutWaitWithCondition {
    private static final Lock lock = new ReentrantLock();
    private static final Condition condition = lock.newCondition();
    private static boolean ready = false;
    protected static final Random random = new Random();

    public static void main(String[] args) throws InterruptedException{
        Thread t = new Thread() {
            @Override
            public void run() {
                for (; ; ) {
                    lock.lock();
                    try {
                        ready = random.nextInt(100) < 5 ? true : false;
                        if (ready) {
                            condition.signal();
                        }
                    } finally {
                        lock.unlock();
                    }

                    Tools.randomPause(500);
                }
            }
        };
        t.setDaemon(true);
        t.start();
        waiter(1000);
    }

    public static void waiter(final long timeOut) throws InterruptedException {
        if (timeOut < 0) {
            throw new InterruptedException();
        }

        final Date deadline = new Date(System.currentTimeMillis() + timeOut);

        boolean continueToWait = true;
        lock.lock();
        try {
            while (!ready) {
                Debug.info("still not ready, continue to wait: %s", continueToWait);

                if (!continueToWait) {
                    Debug.error("Wait timed out , unable to execution target action!");
                    return;
                }
                continueToWait = condition.awaitUntil(deadline);
            }

            guarededAction();
        } finally {
            lock.unlock();
        }
    }

    private static void guarededAction() {

        Debug.info("Take some action.");
    }
}
