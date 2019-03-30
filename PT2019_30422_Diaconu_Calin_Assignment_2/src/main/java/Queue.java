import java.util.ArrayList;
import java.util.List;

public class Queue extends Thread {
    private List<Client> clientList = new ArrayList<Client>();
    private long startTime;
    private boolean runnable;
    private long runningTime;
    private int averageWaitingTime;
    private int totalServiceTime;
    private int totalClients;
    private int emptyQueueTime;
    private long emptyStart;

    private int intervalStart;
    private int intervalEnd;
    private int partialAverageWaitingTime;
    private int partialClients;
    private long partialEmptyStart;
    private int partialEmptyQueue;
    private int partialServiceTime;

    public Queue(long runningTime, int intervalStart, int intervalEnd) {
        this.averageWaitingTime = 0;
        this.partialAverageWaitingTime = 0;
        this.totalClients = 0;
        this.partialClients = 0;
        this.totalServiceTime = 0;
        this.partialServiceTime = 0;
        this.emptyQueueTime = 0;
        this.partialEmptyQueue = 0;
        this.emptyStart = -1;
        this.partialEmptyStart = -1;
        this.intervalStart = intervalStart;
        this.intervalEnd = intervalEnd;
        this.startTime = System.currentTimeMillis();
        this.runningTime = runningTime;
        this.runnable = true;
        this.start();
    }

    public void addClient(Client client) {
        if(((System.currentTimeMillis() - startTime) / 1000 >= intervalStart) && ((System.currentTimeMillis() - startTime) / 1000 <= intervalEnd)) {
            partialServiceTime += client.getServiceTime();
        }

        totalServiceTime += client.getServiceTime();
        clientList.add(client);
    }

    public void run() {
        while(runnable) {
            try {
                if (!clientList.isEmpty()) {
                    Client currentClient = clientList.get(0);

                    averageWaitingTime = (int)((averageWaitingTime * totalClients) + ((System.currentTimeMillis() - currentClient.getBirthTime()) / 1000)) / (totalClients + 1);
                    totalClients++;

                    if(((System.currentTimeMillis() - startTime) / 1000 >= intervalStart) && ((System.currentTimeMillis() - startTime) / 1000 <= intervalEnd)) {
                        partialAverageWaitingTime = (int)((partialAverageWaitingTime * partialClients) + ((System.currentTimeMillis() - currentClient.getBirthTime()) / 1000)) / (partialClients + 1);
                        partialClients++;
                    }

                    sleep(currentClient.getServiceTime() * 1000);
                    clientList.remove(0);

                    if(emptyStart == -1) {
                        emptyStart = System.currentTimeMillis();
                    }
                    if(partialEmptyStart == -1) {
                        if(((System.currentTimeMillis() - startTime) / 1000 >= intervalStart) && ((System.currentTimeMillis() - startTime) / 1000 <= intervalEnd)) {
                            partialEmptyStart = System.currentTimeMillis();
                        }
                    }
                } else {
                    if(emptyStart != -1) {
                        emptyQueueTime += (int)(System.currentTimeMillis() - emptyStart) / 1000;
                        emptyStart = -1;
                    }
                    if(partialEmptyStart != -1) {
                        if(((System.currentTimeMillis() - startTime) / 1000 >= intervalStart) && ((System.currentTimeMillis() - startTime) / 1000 <= intervalEnd)) {
                            partialEmptyQueue += (int)(System.currentTimeMillis() - partialEmptyStart) / 1000;
                            partialEmptyStart = -1;
                        }
                    }
                    if ((System.currentTimeMillis() - startTime) / 1000 >= runningTime) {
                        runnable = false;
                    }
                }
            } catch (InterruptedException e) {
                break;
            }
        }

        clientList.removeAll(clientList);
    }

    public int getTotalWaitingTime() {
        int result = 0;

        for(Client client : clientList) {
            result += client.getServiceTime();
        }

        return result;
    }

    public static boolean allQueuesEmpty(List<Queue> queueList) {
        for(Queue queue : queueList) {
            if(!queue.isEmpty())
                return false;
        }
        return true;
    }

    public static Queue bestQueue(List<Queue> queueList) {
        Queue result = queueList.get(0);
        int min = result.getTotalWaitingTime();

        for(Queue queue : queueList) {
            int totalWaitingTime = queue.getTotalWaitingTime();
            if(totalWaitingTime < min) {
                min = totalWaitingTime;
                result = queue;
            }
        }

        return result;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public boolean isEmpty() {
        return clientList.isEmpty();
    }

    public void setRunnable(boolean runnable) {
        this.runnable = runnable;
    }

    public int getAverageWaitingTime() {
        return averageWaitingTime;
    }

    public int getAverageServiceTime() {
        return totalServiceTime / totalClients;
    }

    public int getEmptyQueueTime() {
        return emptyQueueTime;
    }

    public int getPartialAWT() {
        return partialAverageWaitingTime;
    }

    public int getPartialAST() {
        return partialServiceTime / partialClients;
    }

    public int getPartialEQT() {
        return partialEmptyQueue;
    }

    public List<Client> getClientList() {
        return clientList;
    }
}
