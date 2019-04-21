package BusinessLayer;

import DataLayer.FileWriters;
import DataLayer.RestaurantSerializator;

import javax.swing.*;
import java.util.List;

/**
 * @inv order.getMenuItems() != 0 in computePrice() | computePrice() > 0
 */
public class Restaurant implements RestaurantProcessing {
    public static void createItem(MenuItem menuItem) {
        if (menuItem.getClass().getSimpleName().compareTo("BaseProduct") == 0)
            RestaurantSerializator.addBaseItem((BaseProduct) menuItem);
        else
            RestaurantSerializator.addCompositeItem((CompositeProduct) menuItem);
    }

    public static void deleteItem(MenuItem menuItem) {
        RestaurantSerializator.deleteItem(menuItem);
    }

    public static void createOrder(Order order) {
        RestaurantSerializator.createOrder(order);
    }

    public static String generateBill(Order order) {
        return FileWriters.generateBill(order);
    }

    public static JTable createOrdersTable() {
        return RestaurantSerializator.createOrdersTable();
    }

    public static Order getOrder(int orderID) {
        return RestaurantSerializator.getOrder(orderID);
    }

    public static void deleteOrder(Order order) {
        RestaurantSerializator.deleteOrder(order);
    }

    public static JTable createTable() {
        return RestaurantSerializator.createTable();
    }

    public static void resetStreams() {
        FileWriters.resetStreams();
    }

    public static MenuItem getItem(String name) {
        return RestaurantSerializator.getItem(name);
    }

    public static List<BusinessLayer.MenuItem> getMenuItems() {
        return RestaurantSerializator.getMenuItems();
    }

    public static List<BusinessLayer.MenuItem> getBaseMenuItems() {
        return RestaurantSerializator.getBaseMenuItems();
    }

    public static List<Order> createOrderList() {
        return RestaurantSerializator.createOrderList();
    }
}
