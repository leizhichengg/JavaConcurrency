package ch08;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @Author: withlzc
 * @Description:
 * @Date: Created in 2019-02-19 14:46
 */
public class XThreadFactory implements ThreadFactory {
    final static Logger LOGGER = Logger.getAnonymousLogger();
    private final Thread.UncaughtExceptionHandler ueh;
    private final AtomicInteger threadNumber = new AtomicInteger(1);

    private final String namePrefix;

    public XThreadFactory(Thread.UncaughtExceptionHandler ueh, String namePrefix) {
        this.ueh = ueh;
        this.namePrefix = namePrefix;
    }

    public XThreadFactory(String namePrefix) {
        this.ueh = new LoggingUncaughtExceptionHandler();
        this.namePrefix = namePrefix;
    }

    public XThreadFactory(Thread.UncaughtExceptionHandler ueh) {
        this(ueh, "thread");
    }

    public XThreadFactory() {
        this.ueh = new LoggingUncaughtExceptionHandler();
        this.namePrefix = "thread";
    }

    protected Thread doMakeThread(final Runnable r) {
        return new Thread(r) {
            @Override
            public String toString() {
                ThreadGroup group = getThreadGroup();
                String groupName = null == group ? "" : group.getName();
                String threadInfo = getClass().getSimpleName() + "[" + getName() + ","
                        + getId() + ","
                        + groupName + "]@" + hashCode();
                return threadInfo;
            }
        };
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = doMakeThread(r);
        t.setUncaughtExceptionHandler(ueh);
        t.setName(namePrefix + "-" + threadNumber.getAndIncrement());
        if (t.isDaemon()) {
            t.setDaemon(false);
        }
        if (t.getPriority() != Thread.NORM_PRIORITY) {
            t.setPriority(Thread.NORM_PRIORITY);
        }
        if (LOGGER.isLoggable(Level.FINE)) {
            LOGGER.fine("new thread created" + t);
        }
        return t;
    }

    public class LoggingUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
        @Override
        public void uncaughtException(Thread t, Throwable e) {
            LOGGER.log(Level.SEVERE, t + " terminated:", e);
        }
    }

}



