import java.util.List;

public class ClientGenerator extends Thread {
    private List<Queue> queueList;

    private long stopTime;

    private int minIntervalTime;
    private int maxIntervalTime;

    private int minServiceTime;
    private int maxServiceTime;

    public void run() {
        while(System.nanoTime() < stopTime) {
            long intervalTime = (long) (Math.random() * (maxIntervalTime - minIntervalTime) + minIntervalTime);

            try {
                wait(intervalTime * 1000);
            } catch (InterruptedException e) {
                break;
            }

            Client client = new Client(minServiceTime, maxServiceTime);
            Queue.bestQueue(queueList).addClient(client);
        }
    }
}
