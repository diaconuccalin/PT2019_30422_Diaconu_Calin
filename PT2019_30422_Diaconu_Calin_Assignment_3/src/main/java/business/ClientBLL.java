package business;

import dataAccess.ClientDAO;
import model.Client;

public class ClientBLL {
    public static void addClient(Client client) {
        ClientDAO.addClient(client);
    }

    public static void editClient(Client client) {
        ClientDAO.editClient(client);
    }

    public static Client getClient(int id) {
        return ClientDAO.getClient(id);
    }
}
