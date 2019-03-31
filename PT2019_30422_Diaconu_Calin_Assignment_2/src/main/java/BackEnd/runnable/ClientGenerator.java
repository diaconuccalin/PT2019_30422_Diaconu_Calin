package BackEnd.runnable;

import BackEnd.nonRunnable.Client;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
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
                    Queue.bestQueue(queueList).addClient(client);
                    System.out.println("Added client wt: " + client.getServiceTime());

                    try {
                        writer.append("Added client wt: ").append(String.valueOf(client.getServiceTime())).append("\n");
                    } catch (IOException ignored) {
                    }
                }
            } catch (InterruptedException e) {
                break;
            }
        }

        System.out.println("DONE ADDING\n");

        try {
            writer.append("DONE ADDING");
        } catch (IOException ignored) {
        }
    }

    public static void main(String[] args) {
        List<Queue> queueList = new ArrayList<Queue>();
        int simTime = 20;
        int[] vals = {0, 5, 15, 20, 5, 5, simTime};
        BufferedWriter writer = null;

        try {
            writer = new BufferedWriter(new FileWriter("log.txt"));
        } catch (IOException ignored) {
        }

        queueList.add(new Queue(simTime, 0, 10, writer));
        queueList.add(new Queue(simTime, 0, 10, writer));
        queueList.add(new Queue(simTime, 0, 10, writer));

        ClientGenerator clientGenerator = new ClientGenerator(vals, queueList, writer);
    }
}
