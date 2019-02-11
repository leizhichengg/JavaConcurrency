package ch05;

import util.Tools;
import java.util.concurrent.CountDownLatch;

/**
 * @Author: withlzc
 * @Description:
 * @Date: Created in 2019-02-11 11:07
 */
public class SampleServiceA extends AbstractService {

    public SampleServiceA(CountDownLatch latch) {
        super(latch);
    }

    @Override
    protected void doStart() throws Exception {
        Tools.randomPause(1000);
    }
}
