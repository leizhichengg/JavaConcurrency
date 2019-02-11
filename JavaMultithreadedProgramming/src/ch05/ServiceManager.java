package ch05;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

/**
 * @Author: withlzc
 * @Description:
 * @Date: Created in 2019-02-11 10:42
 */
public class ServiceManager {

    static volatile CountDownLatch latch;
    static Set<Service> services;

    public static void startServices() {
        services = getServices();
        for (Service service : services) {
            service.start();
        }
    }

    public static boolean checkServiceStatus() {
        boolean allIsOk = true;

        try {
            latch.await();
        } catch (InterruptedException e) {
            return false;
        }

        for (Service service : services) {
            if (!service.isStarted()) {
                allIsOk = false;
                break;
            }
        }

        return allIsOk;
    }

    static Set<Service> getServices() {
        latch = new CountDownLatch(3);
        HashSet<Service> services = new HashSet<Service>();
        services.add(new SampleServiceC(latch));
        services.add(new SampleServiceA(latch));
        services.add(new SampleServiceB(latch));
        return services;
    }
}
