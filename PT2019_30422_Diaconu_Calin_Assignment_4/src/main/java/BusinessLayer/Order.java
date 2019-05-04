package BusinessLayer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Order implements Comparable<Order> {
    private static int id = 0;
    private int orderID;
    private LocalDate date;
    private int table;
    private Collection<MenuItem> menuItems;
    private boolean done;

    public Order(int table, List<MenuItem> menuItems) {
        orderID = id;
        id++;
        date = LocalDate.now();
        this.table = table;
        this.menuItems = menuItems;
        done = false;
    }

    public int hashCode() {
        return orderID;
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

    Collection<MenuItem> getMenuItems() {
        return menuItems;
    }

    public List<MenuItem> getListOfItems() {
        return new ArrayList<>(menuItems);
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public int compareTo(Order o) {
        Integer id1 = this.getOrderID();
        Integer id2 = o.getOrderID();

        return id1.compareTo(id2);
    }
}
