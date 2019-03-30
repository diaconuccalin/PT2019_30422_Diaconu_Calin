import java.util.ArrayList;
import java.util.List;

public class Queue extends Thread {
    private List<Client> clientList = new ArrayList<Client>();
    private long startTime;
    private boolean runnable;
    private long runningTime;

    public Queue(long runningTime) {
        this.startTime = System.currentTimeMillis();
        this.runningTime = runningTime;
        this.runnable = true;
        this.start();
    }

    public void addClient(Client client) {
        clientList.add(client);
    }

    public void run() {
        while(runnable) {
            try {
                if (!clientList.isEmpty()) {
                    Client currentClient = clientList.get(0);
                    sleep(currentClient.getServiceTime() * 1000);
                    clientList.remove(0);
                } else if ((System.currentTimeMillis() - startTime) / 1000 >= runningTime) {
                    runnable = false;
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
}
