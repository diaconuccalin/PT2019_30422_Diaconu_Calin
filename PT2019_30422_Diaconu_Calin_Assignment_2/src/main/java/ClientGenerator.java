import java.util.List;

public class ClientGenerator extends Thread {
    private List<Queue> queueList;

    private int minIntervalTime;
    private int maxIntervalTime;

    private int minServiceTime;
    private int maxServiceTime;

    private int minInfoTime;
    private int maxInfoTime;

    private int simTime;

    public ClientGenerator(int[] values, List<Queue> queueList) {
        this.queueList = queueList;

        this.minIntervalTime = values[0];
        this.maxIntervalTime = values[1];

        this.minServiceTime = values[2];
        this.maxServiceTime = values[3];

        this.minInfoTime = values[4];
        this.maxInfoTime = values[5];

        this.simTime = values[6];

        this.start();
    }

    public void run() {
        long startTime = System.currentTimeMillis();
        long stopTime = startTime + simTime * 1000;

        System.out.println("Start");

        while(System.currentTimeMillis() < stopTime) {
            long intervalTime = (long) (Math.random() * (maxIntervalTime - minIntervalTime) + minIntervalTime);

            try {
                sleep(intervalTime * 1000);
            } catch (InterruptedException e) {
                break;
            }

            Client client = new Client(minServiceTime, maxServiceTime);
            Queue.bestQueue(queueList).addClient(client);
        }

        System.out.println("done");

        queueList.clear();
    }
}
