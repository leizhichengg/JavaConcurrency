package ch02;

/**
 * @Author: withlzc
 * @Description:
 * @Date: Created in 2019-01-26 10:36
 */
public class ThreadStartVisibility {

    static int data = 0;

    public static void main(String[] args) {

        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(50);
                    System.out.println(data);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        data = 1;
        thread.start();
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        data = 2;
    }
}
