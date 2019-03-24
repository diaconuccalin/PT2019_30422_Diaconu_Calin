import java.util.ArrayList;
import java.util.List;

public class Queue extends Thread {
    private List<Client> clientList = new ArrayList<Client>();

    public void addClient(Client client) {
        clientList.add(client);
    }

    public void run() {
        while(true) {
            if(!clientList.isEmpty()) {
                Client currentClient = clientList.get(0);

                try {
                    wait(currentClient.getServiceTime() * 1000);
                } catch (InterruptedException e) {
                    break;
                }

                clientList.remove(0);
            }
        }
    }
}
