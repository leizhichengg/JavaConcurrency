package ch05;

import util.Tools;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * @Author: withlzc
 * @Description:
 * @Date: Created in 2019-02-11 11:10
 */
public class SampleServiceC extends AbstractService {

    public SampleServiceC(CountDownLatch latch) {
        super(latch);
    }

    @Override
    protected void doStart() throws Exception {
        Tools.randomPause(2000);

        final Random random = new Random();
        int rand = random.nextInt(1000);
        if (rand <= 500) {
            throw new RuntimeException("test");
        }
    }
}
