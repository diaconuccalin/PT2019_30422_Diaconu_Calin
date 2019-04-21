package BusinessLayer;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class Order implements Serializable {
    private static int id = 0;
    private int orderID;
    private LocalDate date;
    private int table;
    private List<String> menuItems;
    private boolean done;

    public Order(int table, List<String> menuItems) {
        orderID = id;
        id++;
        date = LocalDate.now();
        this.table = table;
        this.menuItems = menuItems;
        done = false;
    }

    public Order(Order order) {
        orderID = id;
        id++;
        date = order.getDate();
        table = order.getTable();
        menuItems = order.getMenuItems();
        done = order.isDone();
    }

    public int hashCode() {
        return -1;
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

    public List<String> getMenuItems() {
        return menuItems;
    }

    public static void resetId() {
        id = 0;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
