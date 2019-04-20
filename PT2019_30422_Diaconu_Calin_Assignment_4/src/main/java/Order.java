import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class Order implements Serializable {
    private static int id = 0;
    private int orderID;
    private LocalDate date;
    private int table;
    private List<MenuItem> menuItems;

    public Order(int table, List<MenuItem> menuItems) {
        orderID = id;
        id++;
        date = LocalDate.now();
        this.table = table;
        this.menuItems = menuItems;
        Main.getChefGraphicalUserInterface().newOrderNotify();
    }

    public int hashCode() {
        return -1;
    }

    public void addMenuItem(MenuItem menuItem) {
        menuItems.add(menuItem);
    }

    public int getOrderID() {
        return orderID;
    }

    public int getTable() {
        return table;
    }

    public LocalDate getDate() {
        return date;
    }
}
