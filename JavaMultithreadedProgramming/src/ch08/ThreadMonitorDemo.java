package ch08;

import util.Debug;
import util.Tools;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @Author: withlzc
 * @Description:
 * @Date: Created in 2019-02-19 14:18
 */
public class ThreadMonitorDemo {
    volatile boolean inited = false;
    static int threadIndex = 0;
    final static Logger LOGGER = Logger.getAnonymousLogger();
    final BlockingQueue<String> channel = new ArrayBlockingQueue<>(100);

    public static void main(String[] args) throws InterruptedException {
        ThreadMonitorDemo demo = new ThreadMonitorDemo();
        demo.init();
        for (int i = 0; i < 100; i++) {
            demo.service("test-" + i);
        }
        Thread.sleep(20000);
        System.exit(0);
    }

    public synchronized void init() {
        if (inited) {
            return;
        }
        Debug.info("init...");
        WorkerThread t = new WorkerThread();
        t.setName("Worker0-" + threadIndex++);
        t.setUncaughtExceptionHandler(new ThreadMonitor());
        t.start();
        inited = true;
    }

    public void service(String message) throws InterruptedException {
        channel.put(message);
    }

    private class ThreadMonitor implements Thread.UncaughtExceptionHandler {
        @Override
        public void uncaughtException(Thread t, Throwable e) {
            Debug.info("Current thread is 't':%s, it is still alive:%s",
                    Thread.currentThread() == t, t.isAlive());

            String threadInfo = t.getName();
            LOGGER.log(Level.SEVERE, threadInfo + " terminated:", e);

            LOGGER.info("About to restart " + threadInfo);
            inited = false;
            init();
        }
    }

    private class WorkerThread extends Thread {
        @Override
        public void run() {
            Debug.info("Do something important...");
            String msg;
            try {
                for (; ; ) {
                    msg = channel.take();
                    process(msg);
                }
            } catch (InterruptedException e) {
            }
        }

        private void process(String message) {
            Debug.info(message);
            if ((int) (Math.random() * 100) < 2) {
                throw new RuntimeException("test");
            }
            Tools.randomPause(100);
        }
    }


}
