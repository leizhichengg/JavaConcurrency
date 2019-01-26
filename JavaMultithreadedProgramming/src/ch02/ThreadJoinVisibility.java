package ch02;

/**
 * @Author: withlzc
 * @Description:
 * @Date: Created in 2019-01-26 10:56
 */
public class ThreadJoinVisibility {

    static int data = 0;

    public static void main(String[] args) {

        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                data = 1;
            }
        };

        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(data);

    }
}
