package DataLayer;

import BusinessLayer.BeforeShutdown;
import BusinessLayer.MenuItem;
import BusinessLayer.Order;
import BusinessLayer.Restaurant;

import java.io.*;
import java.util.List;

public class FileWriters {
    private static BufferedWriter bufferedWriter;

    public static String generateBill(Order order) {
        try {
            String toPrint = "ORDER " + order.getOrderID() + ":\nTable: " + order.getTable() + "   -   " + order.getDate() + "\n";

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
                        menuItem.getName() + " - " + menuItem.computePrice() + " RON x" + quantity + "\n");
                total += menuItem.computePrice() * quantity;
            }

            toPrint = toPrint.concat("\nTOTAL: " + total + " RON\n-------------------------------------------------------------\n");

            bufferedWriter.append(toPrint);
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

    static void importMenu() {
        try {
            File file = new File("menu.ser");
            if (!file.createNewFile() && file.length() != 0) {
                //old menu for input
                FileInputStream fileInputStream = new FileInputStream(file);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

                //fill hashTable
                while (true) {
                    try {
                        MenuItem menuItem = (MenuItem) objectInputStream.readObject();
                        Restaurant.createItem(menuItem);
                    } catch (StreamCorruptedException | EOFException e) {
                        break;
                    } catch (ClassNotFoundException e) {
                        System.out.println(e.toString());
                    }
                }

                //clean old file
                fileInputStream.close();
                file.delete();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void exportMenu() {
        File file = new File("menu.ser");

        try {
            //create output file
            file.createNewFile();

            //output to new file
            FileOutputStream fileOutputStream = new FileOutputStream(file, false);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            List<MenuItem> menuItems = Restaurant.getMenuItems();
            for (MenuItem menuItem : menuItems) {
                objectOutputStream.writeObject(menuItem);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
