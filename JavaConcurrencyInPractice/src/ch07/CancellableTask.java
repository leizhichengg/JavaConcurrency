package ch07;

import java.util.concurrent.Callable;
import java.util.concurrent.RunnableFuture;

/**
 * @Author: lei
 * @Description:
 * @Date: Created in 2019-03-11 21:45
 */
public interface CancellableTask<T> extends Callable<T> {
    void cancel();
    RunnableFuture<T> newTask()
}
