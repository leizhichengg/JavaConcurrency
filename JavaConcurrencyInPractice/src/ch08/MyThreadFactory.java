package ch08;

import java.util.concurrent.ThreadFactory;

/**
 * @Author: lei
 * @Description:
 * @Date: 2019-03-20
 */
public class MyThreadFactory implements ThreadFactory {
    public final String poolName;

    public MyThreadFactory(String poolName) {
        this.poolName = poolName;
    }

    @Override
    public Thread newThread(Runnable r) {
        return new MyAppThread(r, poolName);
    }
}
