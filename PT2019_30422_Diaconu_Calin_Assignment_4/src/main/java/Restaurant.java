/**
 * @inv order.getMenuItems() != 0 in computePrice() | computePrice() > 0
 */
public class Restaurant implements RestaurantProcessing {
    public void createItem(MenuItem menuItem) {}

    public void deleteItem(MenuItem menuItem) {}

    public void editItem(MenuItem menuItem) {}

    public void createOrder(Order order) {}

    public int computePrice(Order order) {
        return -1;
    }

    public void generateBill(Order order) {}
}
