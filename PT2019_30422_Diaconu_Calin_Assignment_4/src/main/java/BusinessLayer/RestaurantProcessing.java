package BusinessLayer;

public interface RestaurantProcessing {
    //Administrator
    static void createItem(MenuItem menuItem) {

    }

    static void deleteItem(MenuItem menuItem) {
    }

    static void editItem(MenuItem menuItem, String newName, int newPrice) {

    }

    //Waiter
    static void createOrder(Order order) {
    }

    static String generateBill(Order order) {
        return "";
    }
}
