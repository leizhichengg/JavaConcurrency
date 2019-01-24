package ch02;

/**
 * @Author: withlzc
 * @Description:
 * @Date: Created in 2019-01-23 19:25
 */
public class RaceConditionDemo {

    public static void main(String[] args) {
        int numberOfThreads = args.length > 0 ? Short.valueOf(args[0]) : Runtime.getRuntime().availableProcessors();
        Thread[] workerThreads = new Thread[numberOfThreads];
        for (int i = 0; i < numberOfThreads; i++) {
            workerThreads[i] = new WorkerThread(i, 10);
        }

        for (Thread ct : workerThreads) {
            ct.start();
        }
    }

    static class WorkerThread extends Thread {
        private final int requestCount;

        public WorkerThread(int id, int requestCount) {
            super("worker-" + id);
            this.requestCount = requestCount;
        }

        @Override
        public void run() {
            int i = requestCount;
            String requestID;
            RequestIDGenerator requestIDGen = RequestIDGenerator.getInstance();
            while (i-- > 0) {
                requestID = requestIDGen.nextID();
                processRequest(requestID);
            }
        }

        private void processRequest(String requestID) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.printf("%s got requestID: %s %n", Thread.currentThread().getName(), requestID);

        }
    }



}
