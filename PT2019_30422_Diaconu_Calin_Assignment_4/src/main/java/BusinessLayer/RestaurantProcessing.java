package BusinessLayer;

public interface RestaurantProcessing {
    //Administrator

    /**
     *
     * @pre menuItem != null
     * @pre menu != null
     *
     * @post menu.size > 0
     */
    static void createItem(MenuItem menuItem) {

    }

    /**
     *
     * @pre menuItem != null
     * @pre menu != null
     */
    static void deleteItem(MenuItem menuItem) {
    }

    /**
     *
     * @pre menuItem != null
     * @pre newPrice >= 0
     *
     * @invariant menu.size()
     */
    static void editItem(MenuItem menuItem, String newName, int newPrice) {

    }

    //Waiter

    /**
     *
     * @pre order != null
     *
     * @post orders.size > 0
     */
    static void createOrder(Order order) {
    }

    /**
     *
     * @pre order != null
     */
    static String generateBill(Order order) {
        return "";
    }
}
