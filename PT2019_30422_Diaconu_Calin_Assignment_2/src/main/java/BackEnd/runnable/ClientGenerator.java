package BackEnd.runnable;

import BackEnd.nonRunnable.Client;
import Main.Main;

import java.io.BufferedWriter;
import java.util.List;

public class ClientGenerator extends Thread {
    private List<Queue> queueList;

    private int minIntervalTime;
    private int maxIntervalTime;

    private int minServiceTime;
    private int maxServiceTime;

    private int simTime;

    private BufferedWriter writer;

    public ClientGenerator(int[] values, List<Queue> queueList, BufferedWriter writer) {
        this.queueList = queueList;

        this.minIntervalTime = values[0];
        this.maxIntervalTime = values[1];

        this.minServiceTime = values[2];
        this.maxServiceTime = values[3];

        this.simTime = values[6];

        this.writer = writer;

        this.start();
    }

    public void run() {
        long startTime = System.currentTimeMillis();
        long stopTime = startTime + simTime * 1000;

        while (System.currentTimeMillis() < stopTime) {
            try {
                long intervalTime = (long) (Math.random() * (maxIntervalTime - minIntervalTime) + minIntervalTime);
                sleep(intervalTime * 1000);
                if (System.currentTimeMillis() < stopTime) {
                    Client client = new Client(minServiceTime, maxServiceTime);
                    System.out.println(Queue.bestQueue(queueList));
                    System.out.println("Added client wt: " + client.getServiceTime());
                    Main.writeToFile(Queue.bestQueue(queueList).toString() + "Added client wt: " + client.getServiceTime() + "\n", writer);
                    Queue.bestQueue(queueList).addClient(client);
                }
            } catch (InterruptedException e) {
                break;
            }
        }
        System.out.println("DONE ADDING");
        Main.writeToFile("DONE ADDING", writer);
    }
}
