package ch05;

/**
 * @Author: withlzc
 * @Description:
 * @Date: Created in 2019-02-11 10:38
 */
public class ServerStarter {

    public static void main(String[] args) {

        ServiceManager.startServices();

        boolean allIsOk;

        allIsOk = ServiceManager.checkServiceStatus();

        if (allIsOk) {
            System.out.println("All services were successfully started!");

        } else {
            System.err.println("Some service(s) failed to start, exiting JVM...");
            System.exit(1);
        }
    }
}
