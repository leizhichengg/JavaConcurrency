package ch01;

/**
 * @Author: withlzc
 * @Description:
 * @Date: Created in 2019/1/13 0013 17:33
 */

public class WelcomeApp {

    public static void main(String[] args) {
        Thread welcomeThread = new WelcomeThread();

        welcomeThread.start();

        System.out.printf("1.Welcome! I'm %s.%n", Thread.currentThread().getName());
    }

}

class WelcomeThread extends Thread {

    @Override
    public void run() {
        System.out.printf("2.Welcome! I'm %s.%n", Thread.currentThread().getName());
    }
}
