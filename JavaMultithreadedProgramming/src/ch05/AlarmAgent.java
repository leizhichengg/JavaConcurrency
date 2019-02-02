package ch05;

import java.util.Random;
import util.Tools;
import util.Debug;

/**
 * @Author: withlzc
 * @Description:
 * @Date: Created in 2019-02-02 15:26
 */
public class AlarmAgent {

    private final static AlarmAgent INSTANCE = new AlarmAgent();

    private boolean connectedToServer = false;

    private final HeartbeartThread hearbearThread = new HeartbeartThread();

    private AlarmAgent() {

    }

    public static AlarmAgent getInstance() {
        return INSTANCE;
    }

    public void init() {
        connectedToServer();
        hearbearThread.setDaemon(true);
        hearbearThread.start();
    }

    private void connectedToServer() {
        new Thread() {
            @Override
            public void run() {
                doConnect();
            }
        }.start();
    }

    private void doConnect() {
        Tools.randomPause(100);
        synchronized (this) {
            connectedToServer = true;
            notify();
        }
    }

    public void sendAlarm (String message) throws InterruptedException {
        synchronized (this) {
            while (!connectedToServer) {
                Debug.info("Alarm agent was not connected to server.");
                wait();
            }
            doSendAlarm(message);
        }
    }

    private void doSendAlarm(String message) {
        Debug.info("Alarm sent: %s", message);
    }

    class HeartbeartThread extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(1000);
                while (true) {
                    if (checkConnection()) {
                        connectedToServer = true;
                    } else {
                        connectedToServer = false;
                        Debug.info("Alarm agent was disconnected from server.");
                        connectedToServer();
                    }
                    Thread.sleep(2000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private boolean checkConnection() {
            boolean isConnected = true;
            final Random random = new Random();
            int rand = random.nextInt(1000);
            if (rand <= 500) {
                isConnected = false;
            }
            return isConnected;

        }
    }

}
