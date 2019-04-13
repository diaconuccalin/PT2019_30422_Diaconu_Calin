package business;

import dataAccess.ClientDAO;
import dataAccess.ReflectionDAO;
import model.Client;

public class ClientBLL {
    public static void addClient(Client client) {
        ReflectionDAO.addElement(client);
    }

    public static void editClient(Client client) {
        ReflectionDAO.editElement(client);
    }

    public static Client getClient(int id) {
        return ClientDAO.getClient(id);
    }
}
