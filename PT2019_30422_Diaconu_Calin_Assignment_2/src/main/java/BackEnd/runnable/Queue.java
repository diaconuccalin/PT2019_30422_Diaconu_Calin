package BackEnd.runnable;

import BackEnd.nonRunnable.Client;
import Main.Main;

import java.io.BufferedWriter;
import java.util.ArrayList;
import java.util.List;

public class Queue extends Thread {
    private List<Client> clientList = new ArrayList<Client>();
    private boolean runnable;
    private long startTime;
    private int runningTime;
    private int totalServiceTime;
    private int totalClients;
    private int averageWaitingTime;
    private int emptyQueueTime;
    private long emptyStart;
    private int intervalStart;
    private int intervalEnd;
    private int partialServiceTime;
    private int partialClients;
    private int partialAverageWaitingTime;
    private int partialEmptyQueue;
    private long partialEmptyStart;
    private BufferedWriter writer;

    public Queue(int runningTime, int intervalStart, int intervalEnd, BufferedWriter writer) {
        this.writer = writer;

        this.runnable = true;
        this.startTime = System.currentTimeMillis();
        this.runningTime = runningTime;
        this.totalServiceTime = 0;
        this.totalClients = 0;
        this.averageWaitingTime = 0;
        this.emptyQueueTime = 0;
        this.emptyStart = -1;

        this.intervalStart = intervalStart;
        this.intervalEnd = intervalEnd;
        this.partialServiceTime = 0;
        this.partialClients = 0;
        this.partialAverageWaitingTime = 0;
        this.partialEmptyQueue = 0;
        this.partialEmptyStart = -1;

        this.start();
    }

    void addClient(Client client) {
        totalServiceTime += client.getServiceTime();
        clientList.add(client);
    }

    public void run() {
        while (runnable) {
            System.out.print(""); //without this it wouldn't start immediately
            try {
                if ((System.currentTimeMillis() - startTime) / 1000 > intervalEnd && partialEmptyStart != -1) {
                    partialEmptyQueue += (System.currentTimeMillis() - partialEmptyStart) / 1000;
                    partialEmptyStart = -1;
                }
                if (!clientList.isEmpty()) {
                    Client currentClient = clientList.get(0);
                    averageWaitingTime = (int) ((averageWaitingTime * totalClients) + ((System.currentTimeMillis() - currentClient.getBirthTime()) / 1000)) / (totalClients + 1);
                    totalClients++;
                    if (((System.currentTimeMillis() - startTime) / 1000 >= intervalStart) && ((System.currentTimeMillis() - startTime) / 1000 <= intervalEnd)) {
                        partialAverageWaitingTime = (int) ((partialAverageWaitingTime * partialClients) + ((System.currentTimeMillis() - currentClient.getBirthTime()) / 1000)) / (partialClients + 1);
                        partialServiceTime += currentClient.getServiceTime();
                        partialClients++;
                    }
                    System.out.println(this.toString() + "Processing client wt: " + currentClient.getServiceTime() + " " + currentClient.getName());
                    Main.writeToFile(this.toString() + "Processing client wt: " + currentClient.getServiceTime() + " " + currentClient.getName() + "\n", writer);
                    sleep(currentClient.getServiceTime() * 1000);
                    System.out.println("DONE CLIENT " + currentClient.getName());
                    Main.writeToFile("DONE CLIENT " + currentClient.getName() + "\n", writer);
                    clientList.remove(0);
                    if (emptyStart != -1) {
                        emptyQueueTime += (int) (System.currentTimeMillis() - emptyStart) / 1000;
                        emptyStart = -1;
                    }
                    if (partialEmptyStart != -1 && (((System.currentTimeMillis() - startTime) / 1000 >= intervalStart) && ((System.currentTimeMillis() - startTime) / 1000 <= intervalEnd))) {
                        partialEmptyQueue += (int) (System.currentTimeMillis() - partialEmptyStart) / 1000;
                        partialEmptyStart = -1;
                    }
                } else {
                    if (emptyStart == -1)
                        emptyStart = System.currentTimeMillis();
                    if (partialEmptyStart == -1 && (((System.currentTimeMillis() - startTime) / 1000 >= intervalStart) && ((System.currentTimeMillis() - startTime) / 1000 <= intervalEnd)))
                        partialEmptyStart = System.currentTimeMillis();
                    if ((System.currentTimeMillis() - startTime) / 1000 >= runningTime)
                        runnable = false;
                }
            } catch (InterruptedException e) {
                break;
            }
        }
        if (emptyStart != -1)
            emptyQueueTime += (int) (System.currentTimeMillis() - emptyStart) / 1000;
        if (partialEmptyStart != -1)
            partialEmptyQueue += (int) (System.currentTimeMillis() - partialEmptyStart) / 1000;
        System.out.println(this.toString() + " QUEUE DONE");
        Main.writeToFile(this.toString() + "QUEUE DONE\n", writer);
    }

    static Queue bestQueue(List<Queue> queueList) {
        Queue result = queueList.get(0);
        int min = result.getTotalWaitingTime();

        for (Queue queue : queueList) {
            int totalWaitingTime = queue.getTotalWaitingTime();
            if (totalWaitingTime < min) {
                min = totalWaitingTime;
                result = queue;
            }
        }

        return result;
    }

    private int getTotalWaitingTime() {
        int result = 0;

        for (Client client : clientList) {
            result += client.getServiceTime();
        }

        return result;
    }

    static boolean allQueuesEmpty(List<BackEnd.runnable.Queue> queueList) {
        for (BackEnd.runnable.Queue queue : queueList) {
            if (!queue.isEmpty())
                return false;
        }
        return true;
    }

    private boolean isEmpty() {
        return clientList.isEmpty();
    }

    int getAverageWaitingTime() {
        return averageWaitingTime;
    }

    int getAverageServiceTime() {
        if (totalClients == 0)
            return 0;
        else
            return totalServiceTime / totalClients;
    }

    int getEmptyQueueTime() {
        return emptyQueueTime;
    }

    int getPartialAWT() {
        return partialAverageWaitingTime;
    }

    int getPartialAST() {
        if (partialClients == 0)
            return 0;
        else
            return partialServiceTime / partialClients;
    }

    int getPartialEQT() {
        return partialEmptyQueue;
    }

    public List<BackEnd.nonRunnable.Client> getClientList() {
        return clientList;
    }
}
