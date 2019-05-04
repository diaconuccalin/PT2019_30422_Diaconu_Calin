package DataLayer;

import BusinessLayer.BeforeShutdown;
import BusinessLayer.MenuItem;
import BusinessLayer.Order;
import BusinessLayer.Restaurant;

import java.io.*;
import java.util.List;

public class FileWriters {
    private static FileOutputStream fileOutputStream;
    private static ObjectOutputStream objectOutputStream;

    private static FileInputStream fileInputStream;
    private static ObjectInputStream objectInputStream;

    private static BufferedWriter bufferedWriter;

    public static String generateBill(Order order) {
        try {
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

    public static void importMenu() {
        try {
            File file = new File("menu.ser");
            if (!file.createNewFile() && file.length() != 0) {
                //old menu for input
                fileInputStream = new FileInputStream(file);
                objectInputStream = new ObjectInputStream(fileInputStream);

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

    public static void exportMenu() {
        File file = new File("menu.ser");

        try {
            //create output file
            file.createNewFile();

            //output to new file
            fileOutputStream = new FileOutputStream(file, false);
            objectOutputStream = new ObjectOutputStream(fileOutputStream);

            List<MenuItem> menuItems = Restaurant.getMenuItems();
            for(MenuItem menuItem : menuItems) {
                objectOutputStream.writeObject(menuItem);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
