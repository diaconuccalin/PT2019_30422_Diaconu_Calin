import javax.swing.*;
import java.io.EOFException;
import java.io.IOException;
import java.io.StreamCorruptedException;
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

    public static JTable createTable() {
        JTable jTable = new JTable();
        List<MenuItem> menuItems = new ArrayList<MenuItem>();

        while(true) {
            try {
                menuItems.add((MenuItem) FileWriter.getObjectInputStream().readObject());
            } catch (StreamCorruptedException | EOFException e) {
                break;
            } catch (IOException | ClassNotFoundException e) {
                System.out.println(e);
            }
        }

        String[] columnNames = {"Name", "Price", "Type"};
        Object[][] data = new Object[menuItems.size()][3];

        for(int i = 0; i < menuItems.size(); i++) {
            data[i][0] = menuItems.get(i).getName();
            data[i][1] = menuItems.get(i).computePrice();
            data[i][2] = menuItems.get(i).getClass().getSimpleName();
        }

        jTable = new JTable(data, columnNames);
        return jTable;
    }
}
