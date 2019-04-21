package BusinessLayer;

public interface RestaurantProcessing {
    //Administrator

    /**
     * @pre menuItem != null
     * @post menuItem.getName().compareTo(menuItem @ pre.getName ()) == 0
     */
    static void createItem(MenuItem menuItem) {
    }

    /**
     * @pre menuItem != null
     */
    static void deleteItem(MenuItem menuItem) {
    }

    //Waiter

    /**
     * @pre order != null
     */
    static void createOrder(Order order) {
    }

    /**
     * @pre order != null
     */
    static String generateBill(Order order) {
        return "";
    }
}
