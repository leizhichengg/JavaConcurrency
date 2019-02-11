package ch05;

import util.Tools;
import java.util.concurrent.CountDownLatch;

/**
 * @Author: withlzc
 * @Description:
 * @Date: Created in 2019-02-11 11:09
 */
public class SampleServiceB extends AbstractService {

    public SampleServiceB(CountDownLatch latch) {
        super(latch);
    }

    @Override
    protected void doStart() throws Exception {
        Tools.randomPause(2000);
    }

}
