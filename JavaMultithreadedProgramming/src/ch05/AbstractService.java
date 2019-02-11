package ch05;
import util.Debug;
import java.util.concurrent.CountDownLatch;

/**
 * @Author: withlzc
 * @Description:
 * @Date: Created in 2019-02-11 10:56
 */
public abstract class AbstractService implements Service {
    protected boolean started = false;
    protected final CountDownLatch latch;

    public AbstractService(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public boolean isStarted() {
        return started;
    }

    protected abstract void doStart() throws Exception;

    @Override
    public void start() {
        new ServiceStarter().start();
    }

    @Override
    public void stop() {

    }

    class ServiceStarter extends Thread {
        @Override
        public void run() {
            final String serviceName = AbstractService.this.getClass().getSimpleName();
            Debug.info("Starting %s", serviceName);
            try {
                doStart();
                started = true;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                latch.countDown();
                Debug.info("Done Starting %s", serviceName);
            }
        }
    }


}
