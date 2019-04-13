package business;

import dataAccess.OrderDAO;
import model.Order;

public class OrderBLL {
    public static void addOrder(Order order) {
        OrderDAO.addOrder(order);
    }
}
