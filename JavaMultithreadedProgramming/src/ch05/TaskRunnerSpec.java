package ch05;

/**
 * @Author: withlzc
 * @Description:
 * @Date: Created in 2019-02-13 13:48
 */
public interface TaskRunnerSpec {

    public void init();

    public void submit(Runnable task) throws InterruptedException;
}
