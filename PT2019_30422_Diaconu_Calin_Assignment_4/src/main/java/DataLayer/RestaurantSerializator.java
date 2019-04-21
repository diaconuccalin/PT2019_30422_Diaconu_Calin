package DataLayer;

import BusinessLayer.BaseProduct;
import BusinessLayer.CompositeProduct;
import BusinessLayer.MenuItem;
import BusinessLayer.Order;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RestaurantSerializator {
    public static void addBaseItem(BaseProduct baseProduct) {
        try {
            FileWriters.getObjectOutputStream().writeObject(baseProduct);
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    public static void addCompositeItem(CompositeProduct compositeProduct) {
        try {
            FileWriters.getObjectOutputStream().writeObject(compositeProduct);
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    public static void deleteItem(MenuItem menuItem) {
        FileWriters.resetStreams();
        List<MenuItem> menuItems = new ArrayList<>();

        while (true) {
            try {
                MenuItem menuItem1 = (MenuItem) FileWriters.getObjectInputStream().readObject();
                if (menuItem1.getName().compareTo(menuItem.getName()) != 0) {
                    menuItems.add(menuItem1);
                }
            } catch (StreamCorruptedException | EOFException e) {
                break;
            } catch (ClassNotFoundException | IOException e) {
                System.out.println(e);
            }
        }

        try {
            File file = new File("menu.ser");
            FileOutputStream fileOutputStream = new FileOutputStream(file, false);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            for (MenuItem menuItem1 : menuItems) {
                objectOutputStream.writeObject(menuItem1);
            }

            FileWriters.resetStreams();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    public static void createOrder(Order order) {
        FileWriters.resetStreams();
        try {
            FileWriters.getOrderObjectOutputStream().writeObject(order);
        } catch (IOException e) {
            System.out.println(e.toString());
        }
        FileWriters.resetStreams();
    }

    public static JTable createOrdersTable() {
        FileWriters.resetStreams();
        JTable jTable;
        List<Order> orders = new ArrayList<>();

        while (true) {
            try {
                orders.add((Order) FileWriters.getOrderObjectInputStream().readObject());
            } catch (IOException e) {
                break;
            } catch (ClassNotFoundException e) {
                System.out.println(e.toString());
            }
        }
        String[] columnNames = {"Id", "Table", "Date"};
        Object[][] data = new Object[orders.size()][3];

        for (int i = 0; i < orders.size(); i++) {
            data[i][0] = orders.get(i).getOrderID();
            data[i][1] = orders.get(i).getTable();
            data[i][2] = orders.get(i).getDate();
        }

        jTable = new JTable(data, columnNames);
        jTable.setDefaultEditor(Object.class, null);

        FileWriters.resetStreams();
        return jTable;
    }

    public static Order getOrder(int orderID) {
        FileWriters.resetStreams();
        while (true) {
            try {
                Order order = (Order) FileWriters.getOrderObjectInputStream().readObject();
                if (order.getOrderID() == orderID) {
                    return order;
                }
            } catch (StreamCorruptedException | EOFException e) {
                break;
            } catch (ClassNotFoundException | IOException e) {
                System.out.println(e);
            }
        }
        FileWriters.resetStreams();
        return null;
    }

    public static void deleteOrder(Order order) {
        FileWriters.resetStreams();
        List<Order> orders = new ArrayList<>();

        while (true) {
            try {
                Order order1 = (Order) FileWriters.getOrderObjectInputStream().readObject();
                if (order1.getOrderID() != order.getOrderID()) {
                    orders.add(order1);
                }
            } catch (StreamCorruptedException | EOFException e) {
                break;
            } catch (ClassNotFoundException | IOException e) {
                System.out.println(e);
            }
        }

        try {
            File file = new File("orders.ser");
            FileOutputStream fileOutputStream = new FileOutputStream(file, false);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            for (Order order1 : orders) {
                objectOutputStream.writeObject(order1);
            }

            FileWriters.resetStreams();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    public static JTable createTable() {
        JTable jTable;
        List<MenuItem> menuItems = new ArrayList<>();

        while (true) {
            try {
                menuItems.add((MenuItem) FileWriters.getObjectInputStream().readObject());
            } catch (IOException e) {
                break;
            } catch (ClassNotFoundException e) {
                System.out.println(e.toString());
            }
        }
        String[] columnNames = {"Name", "Price", "Type"};
        Object[][] data = new Object[menuItems.size()][3];

        for (int i = 0; i < menuItems.size(); i++) {
            data[i][0] = menuItems.get(i).getName();
            data[i][1] = menuItems.get(i).computePrice();
            data[i][2] = menuItems.get(i).getClass().getSimpleName();
        }

        jTable = new JTable(data, columnNames);
        jTable.setDefaultEditor(Object.class, null);


        return jTable;
    }

    public static MenuItem getItem(String name) {
        FileWriters.resetStreams();
        while (true) {
            try {
                MenuItem menuItem = (MenuItem) FileWriters.getObjectInputStream().readObject();
                if (menuItem.getName().compareTo(name) == 0) {
                    return menuItem;
                }
            } catch (StreamCorruptedException | EOFException e) {
                break;
            } catch (ClassNotFoundException | IOException e) {
                System.out.println(e);
            }
        }
        FileWriters.resetStreams();
        return null;
    }

    public static List<BusinessLayer.MenuItem> getMenuItems() {
        List<BusinessLayer.MenuItem> menuItems = new ArrayList<>();
        while (true) {
            try {
                menuItems.add((MenuItem) FileWriters.getObjectInputStream().readObject());
            } catch (StreamCorruptedException | EOFException e) {
                break;
            } catch (IOException | ClassNotFoundException e) {
                System.out.println(e);
            }
        }

        return menuItems;
    }

    public static List<BusinessLayer.MenuItem> getBaseMenuItems() {
        List<BusinessLayer.MenuItem> menuItems = new ArrayList<>();
        while (true) {
            try {
                BusinessLayer.MenuItem menuItem1 = (MenuItem) FileWriters.getObjectInputStream().readObject();
                if (menuItem1.getClass().getSimpleName().compareTo("BaseProduct") == 0)
                    menuItems.add(menuItem1);
            } catch (StreamCorruptedException | EOFException e) {
                break;
            } catch (IOException | ClassNotFoundException e) {
                System.out.println(e);
            }
        }

        return menuItems;
    }

    public static List<Order> createOrderList() {
        List<Order> orders = new ArrayList<>();
        while (true) {
            try {
                Order order = (Order) FileWriters.getOrderObjectInputStream().readObject();
                if (!order.isDone())
                    orders.add(order);
            } catch (StreamCorruptedException | EOFException e) {
                break;
            } catch (IOException | ClassNotFoundException e) {
                System.out.println(e);
            }
        }

        return orders;
    }
}
