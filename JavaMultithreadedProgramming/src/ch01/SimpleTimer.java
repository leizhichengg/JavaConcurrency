package ch01;

/**
 * @Author: withlzc
 * @Description:
 * @Date: Created in 2019/1/15 0015 17:09
 */
public class SimpleTimer {
    private static int count;

    public static void main(String[] args) {
        count = args.length >= 1 ? Integer.valueOf(args[0]) : 60;
        int remaining;
        while (true) {
            remaining = countDown();
            if (0 == remaining) {
                break;
            } else {
                System.out.println("Remaining " + count + " seconds(s).");
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Done.");
    }

    private static int countDown() {
        return count--;
    }
}
