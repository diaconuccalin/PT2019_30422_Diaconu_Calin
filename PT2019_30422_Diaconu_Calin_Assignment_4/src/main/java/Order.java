import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private int orderID;
    private LocalDate date;
    private int table;
    private List<MenuItem> menuItems = new ArrayList<MenuItem>();

    public Order(ChefGraphicalUserInterface chefGUI) {
        chefGUI.newOrderNotify();
    }

    public int hashCode() {
        return -1;
    }

    public void addMenuItem(MenuItem menuItem) {
        menuItems.add(menuItem);
    }
}
