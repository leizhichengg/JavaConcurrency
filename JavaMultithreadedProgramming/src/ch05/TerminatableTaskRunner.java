package ch05;

import util.Debug;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author: withlzc
 * @Description:
 * @Date: Created in 2019-02-13 13:50
 */
public class TerminatableTaskRunner implements TaskRunnerSpec {

    protected final BlockingQueue<Runnable> channel;

    protected volatile boolean inUse = true;

    public final AtomicInteger reservations = new AtomicInteger(0);
    private volatile Thread workerThread;

    public TerminatableTaskRunner(BlockingQueue<Runnable> channel) {
        this.channel = channel;
        this.workerThread = new WorkerThread();
    }

    public TerminatableTaskRunner() {
        this(new LinkedBlockingQueue<Runnable>());
    }

    @Override
    public void init() {
        final Thread t = workerThread;
        if (null != t) {
            t.start();
        }
    }

    @Override
    public void submit(Runnable task) throws InterruptedException {
        channel.put(task);
        reservations.incrementAndGet();
    }

    public void shutdown() {
        Debug.info("Shutting down service...");
        inUse = false;
        final Thread t = workerThread;
        if (null != t) {
            t.interrupt();
        }
    }

    class WorkerThread extends Thread {
        @Override
        public void run() {
            Runnable task = null;
            try {
                for (; ; ) {
                    if (!inUse && reservations.get() <= 0) {
                        break;
                    }
                    task = channel.take();
                    try {
                        task.run();
                    } catch (Throwable e) {
                        e.printStackTrace();
                    }
                    reservations.decrementAndGet();
                }
            } catch (InterruptedException e) {
                workerThread = null;
            }
            Debug.info("worker thread terminated.");
        }
    }

}
