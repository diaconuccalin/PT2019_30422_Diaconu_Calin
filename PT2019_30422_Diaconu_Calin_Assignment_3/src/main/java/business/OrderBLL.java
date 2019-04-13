package business;

import dataAccess.ReflectionDAO;
import model.Order;

public class OrderBLL {
    public static void addOrder(Order order) {
        ReflectionDAO.addElement(order);
    }
}
