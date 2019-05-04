package BusinessLayer;

import DataLayer.FileWriters;

import javax.swing.*;
import java.util.*;
import java.util.List;

public class Restaurant implements RestaurantProcessing {
    private static Map<Order, Collection<MenuItem>> orders = new HashMap<>();
    private static List<MenuItem> menu = new ArrayList<>();

    public static void createItem(MenuItem menuItem) {
        menu.add(menuItem);
    }

    public static void deleteItem(MenuItem menuItem) {
        menu.remove(getItem(menuItem.getName()));
    }

    public static void createOrder(Order order) {
        orders.put(order, order.getMenuItems());
    }

    public static String generateBill(Order order) {
        return FileWriters.generateBill(order);
    }

    public static JTable createOrdersTable() {
        List<Order> orderList = createOrderList();

        JTable jTable;
        String[] columnNames = {"Id", "Table", "Date"};
        Object[][] data = new Object[orderList.size()][3];

        for (int i = 0; i < orderList.size(); i++) {
            data[i][0] = orderList.get(i).getOrderID();
            data[i][1] = orderList.get(i).getTable();
            data[i][2] = orderList.get(i).getDate();
        }

        jTable = new JTable(data, columnNames);
        jTable.setDefaultEditor(Object.class, null);

        return jTable;
    }

    public static Order getOrder(int orderID) {
        List<Order> orderList = createOrderList();

        for (Order order : orderList) {
            if (order.getOrderID() == orderID)
                return order;
        }

        return null;
    }

    public static void deleteOrder(Order order) {
        orders.remove(getOrder(order.getOrderID()));
    }

    public static JTable createTable() {
        JTable jTable;

        String[] columnNames = {"Name", "Price", "Type"};
        Object[][] data = new Object[menu.size()][3];

        for (int i = 0; i < menu.size(); i++) {
            data[i][0] = menu.get(i).getName();
            data[i][1] = menu.get(i).computePrice();
            data[i][2] = menu.get(i).getClass().getSimpleName();
        }

        jTable = new JTable(data, columnNames);
        jTable.setDefaultEditor(Object.class, null);

        return jTable;
    }

    public static MenuItem getItem(String name) {
        for (MenuItem menuItem : menu) {
            if (menuItem.getName().compareTo(name) == 0)
                return menuItem;
        }

        return null;
    }

    public static List<MenuItem> getMenuItems() {
        return menu;
    }

    public static List<MenuItem> getBaseMenuItems() {
        List<MenuItem> toReturn = new ArrayList<>();

        for (MenuItem menuItem : menu) {
            if (menuItem.getClass().getSimpleName().compareTo("BaseProduct") == 0)
                toReturn.add(menuItem);
        }
        return toReturn;
    }

    public static List<Order> createOrderList() {
        Set<Order> orderSet = orders.keySet();
        List<Order> orderList = new ArrayList<>(orderSet);
        Collections.sort(orderList);

        return orderList;
    }

    public static void editItem(MenuItem menuItem, String newName, int newPrice) {
        for (MenuItem menuItem1 : menu) {
            if (menuItem1.getName().compareTo(menuItem.getName()) == 0) {
                menuItem1.setName(newName);
                menuItem1.setPrice(newPrice);
                break;
            }
        }
    }

    public static void updatePrices() {
        for (MenuItem menuItem : menu) {
            menuItem.computePrice();
        }
    }

    public static boolean canBeDeleted(String name) {
        if (getItem(name).getClass().getSimpleName().compareTo("CompositeProduct") == 0)
            return true;

        for (MenuItem menuItem : menu) {
            if (menuItem.getClass().getSimpleName().compareTo("CompositeProduct") == 0) {
                CompositeProduct compositeProduct = (CompositeProduct) menuItem;

                List<MenuItem> ingredients = compositeProduct.getIngredients();
                for (MenuItem menuItem1 : ingredients) {
                    if (name.compareTo(menuItem1.getName()) == 0)
                        return false;
                }
            }
        }
        return true;
    }
}
