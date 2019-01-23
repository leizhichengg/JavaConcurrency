package ch01;

/**
 * @Author: withlzc
 * @Description:
 * @Date: Created in 2019/1/13 0013 17:34
 */
public class WelcomeApp1 {

    public static void main(String[] args) {

        Thread welcomeThread = new Thread(new WelcomeTask());

        welcomeThread.start();

        System.out.printf("1.Welcome! I'm %s.%n", Thread.currentThread().getName());
    }

}

class WelcomeTask implements Runnable {

    @Override
    public void run() {
        System.out.printf("2.Welcome! I'm %s.%n", Thread.currentThread().getName());
    }
}