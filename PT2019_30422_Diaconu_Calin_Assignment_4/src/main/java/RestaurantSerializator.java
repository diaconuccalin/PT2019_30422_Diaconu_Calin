import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RestaurantSerializator {
    public static void addBaseItem(BaseProduct baseProduct) {
        try {
            FileWriter.getObjectOutputStream().writeObject(baseProduct);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void addCompositeItem(CompositeProduct compositeProduct) {
        try {
            FileWriter.getObjectOutputStream().writeObject(compositeProduct);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static JTable createTable() {
        JTable jTable;
        List<MenuItem> menuItems = new ArrayList<MenuItem>();

        while (true) {
            try {
                menuItems.add((MenuItem) FileWriter.getObjectInputStream().readObject());
            } catch (IOException e) {
                break;
            } catch (ClassNotFoundException e) {
                System.out.println(e);
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

    public static void deleteItem(MenuItem menuItem){
        new FileWriter();
        List<MenuItem> menuItems = new ArrayList<>();

        while(true) {
            try {
                MenuItem menuItem1 = (MenuItem) FileWriter.getObjectInputStream().readObject();
                if(menuItem1.getName().compareTo(menuItem.getName()) != 0) {
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

            for(MenuItem menuItem1 : menuItems) {
                objectOutputStream.writeObject(menuItem1);
            }

            new FileWriter();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static MenuItem getItem(String name) {
        while(true) {
            try {
                MenuItem menuItem = (MenuItem) FileWriter.getObjectInputStream().readObject();
                if(menuItem.getName().compareTo(name) == 0) {
                    return menuItem;
                }
            } catch (StreamCorruptedException | EOFException e) {
                break;
            } catch (ClassNotFoundException | IOException e) {
                System.out.println(e);
            }
        }
        new FileWriter();
        return null;
    }

    public static JTable createOrdersTable() {
        return null;
    }
}
