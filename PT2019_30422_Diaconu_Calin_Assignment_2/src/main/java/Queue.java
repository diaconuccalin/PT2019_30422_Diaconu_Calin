import java.util.ArrayList;
import java.util.List;

public class Queue extends Thread {
    private List<Client> clientList = new ArrayList<Client>();
    private long startTime;
    private boolean runnable;
    private long runningTime;

    public Queue(long runningTime) {
        this.runningTime = runningTime;
        this.runnable = true;
        this.start();
    }

    public void addClient(Client client) {
        clientList.add(client);
    }

    public void run() {
        while(runnable) {
            if(!clientList.isEmpty()) {
                Client currentClient = clientList.get(0);

                try {
                    sleep(currentClient.getServiceTime() * 1000);
                } catch (InterruptedException e) {
                    break;
                }

                clientList.remove(0);
            } else if((System.currentTimeMillis() - startTime) / 1000 >= runningTime) {
                runnable = false;
            }
        }
    }

    public int getTotalWaitingTime() {
        int result = 0;

        for(Client client : clientList) {
            result += client.getServiceTime();
        }

        return result;
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
