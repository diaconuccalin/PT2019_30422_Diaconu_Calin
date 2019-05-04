package DataLayer;

import BusinessLayer.BeforeShutdown;
import BusinessLayer.MenuItem;
import BusinessLayer.Order;
import BusinessLayer.Restaurant;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileWriters {
    private static FileOutputStream fileOutputStream;
    private static ObjectOutputStream objectOutputStream;

    private static FileInputStream fileInputStream;
    private static ObjectInputStream objectInputStream;

    private static FileOutputStream orderFileOutputStream;
    private static ObjectOutputStream orderObjectOutputStream;

    private static FileInputStream orderFileInputStream;
    private static ObjectInputStream orderObjectInputStream;

    private static BufferedWriter bufferedWriter;

    public static void resetStreams() {
        try {
            //already existing file for input
            File file = new File("menu.ser");
            File auxFile = new File("helpFile.ser");
            if (!file.createNewFile() && file.length() != 0) {
                //old menu for input
                fileInputStream = new FileInputStream(file);
                objectInputStream = new ObjectInputStream(fileInputStream);

                //aux file for output
                auxFile.createNewFile();
                FileOutputStream fileOutputStream1 = new FileOutputStream(auxFile, false);
                ObjectOutputStream objectOutputStream1 = new ObjectOutputStream(fileOutputStream1);

                //actual output
                MenuItem menuItem;
                while (true) {
                    try {
                        menuItem = (MenuItem) objectInputStream.readObject();
                        objectOutputStream1.writeObject(menuItem);
                    } catch (StreamCorruptedException | EOFException e) {
                        break;
                    } catch (ClassNotFoundException e) {
                        System.out.println(e.toString());
                    }
                }

                //clean old file
                fileInputStream.close();
                file.delete();

                //recreate
                file.createNewFile();

                //prepare aux for input
                FileInputStream fileInputStream1 = new FileInputStream(auxFile);
                ObjectInputStream objectInputStream1 = new ObjectInputStream(fileInputStream1);

                //output to new file
                fileOutputStream = new FileOutputStream(file, false);
                objectOutputStream = new ObjectOutputStream(fileOutputStream);
                while (true) {
                    try {
                        menuItem = (MenuItem) objectInputStream1.readObject();
                        objectOutputStream.writeObject(menuItem);
                    } catch (StreamCorruptedException | EOFException e) {
                        break;
                    } catch (ClassNotFoundException e) {
                        System.out.println(e.toString());
                    }
                }

                //clean aux file
                fileInputStream1.close();
                fileOutputStream1.close();
                auxFile.delete();

                //prepare input
                fileInputStream = new FileInputStream(file);
                objectInputStream = new ObjectInputStream(fileInputStream);
            } else {
                fileOutputStream = new FileOutputStream(file, true);
                objectOutputStream = new ObjectOutputStream(fileOutputStream);
                fileInputStream = new FileInputStream(file);
                objectInputStream = new ObjectInputStream(fileInputStream);
            }
        } catch (IOException e) {
            System.out.println(e.toString());
        }

        try {
            //already existing file for input
            File file = new File("orders.ser");
            File auxFile = new File("helpFile.ser");
            if (!file.createNewFile() && file.length() != 0) {
                //old menu for input
                orderFileInputStream = new FileInputStream(file);
                orderObjectInputStream = new ObjectInputStream(orderFileInputStream);

                //aux file for output
                auxFile.createNewFile();
                FileOutputStream fileOutputStream1 = new FileOutputStream(auxFile, false);
                ObjectOutputStream objectOutputStream1 = new ObjectOutputStream(fileOutputStream1);

                //actual output
                Order order;
                while (true) {
                    try {
                        order = new Order((Order) orderObjectInputStream.readObject());
                        objectOutputStream1.writeObject(order);
                    } catch (StreamCorruptedException | EOFException e) {
                        break;
                    } catch (ClassNotFoundException e) {
                        System.out.println(e.toString());
                    }
                }

                //clean old file
                orderFileInputStream.close();
                file.delete();

                //recreate
                file.createNewFile();

                //prepare aux for input
                FileInputStream fileInputStream1 = new FileInputStream(auxFile);
                ObjectInputStream objectInputStream1 = new ObjectInputStream(fileInputStream1);

                //output to new file
                orderFileOutputStream = new FileOutputStream(file, false);
                orderObjectOutputStream = new ObjectOutputStream(orderFileOutputStream);
                while (true) {
                    try {
                        order = (Order) objectInputStream1.readObject();
                        orderObjectOutputStream.writeObject(order);
                    } catch (StreamCorruptedException | EOFException e) {
                        break;
                    } catch (ClassNotFoundException e) {
                        System.out.println(e.toString());
                    }
                }

                //clean aux file
                fileInputStream1.close();
                fileOutputStream1.close();
                auxFile.delete();

                //prepare input
                orderFileInputStream = new FileInputStream(file);
                orderObjectInputStream = new ObjectInputStream(orderFileInputStream);
            } else {
                orderFileOutputStream = new FileOutputStream(file, true);
                orderObjectOutputStream = new ObjectOutputStream(orderFileOutputStream);
                orderFileInputStream = new FileInputStream(file);
                orderObjectInputStream = new ObjectInputStream(orderFileInputStream);
            }
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    static ObjectOutputStream getObjectOutputStream() {
        return objectOutputStream;
    }

    static ObjectInputStream getObjectInputStream() {
        return objectInputStream;
    }

    static ObjectOutputStream getOrderObjectOutputStream() {
        return orderObjectOutputStream;
    }

    static ObjectInputStream getOrderObjectInputStream() {
        return orderObjectInputStream;
    }

    public static String generateBill(Order order) {
        try {
            FileWriters.resetStreams();
            String toPrint = "ORDER " +
                    order.getOrderID() +
                    ":\n" +
                    "Table: " +
                    order.getTable() +
                    "   -   " +
                    order.getDate() +
                    "\n";

            List<MenuItem> menuItems = order.getListOfItems();
            int total = 0;
            for (int i = 0; i < menuItems.size(); i++) {
                int quantity = 1;
                while (i != menuItems.size() - 1 && menuItems.get(i).getName().compareTo(menuItems.get(i + 1).getName()) == 0) {
                    quantity++;
                    i++;
                }

                MenuItem menuItem = Restaurant.getItem(menuItems.get(i).getName());
                toPrint = toPrint.concat(
                        menuItem.getName() +
                                " - " +
                                menuItem.computePrice() +
                                " RON x" +
                                quantity +
                                "\n");
                total += menuItem.computePrice() * quantity;
            }

            toPrint = toPrint.concat("\nTOTAL: " +
                    total +
                    " RON\n-------------------------------------------------------------\n");

            bufferedWriter.append(toPrint);
            FileWriters.resetStreams();
            return toPrint;
        } catch (IOException e) {
            System.out.println(e.toString());
        }
        return null;
    }

    public static void initializeBufferedWriter() {
        try {
            bufferedWriter = new BufferedWriter(new FileWriter("bills.txt"));
        } catch (IOException e) {
            System.out.println(e.toString());
        }

        Runtime runtime = Runtime.getRuntime();
        runtime.addShutdownHook(new BeforeShutdown(bufferedWriter));
    }
}
