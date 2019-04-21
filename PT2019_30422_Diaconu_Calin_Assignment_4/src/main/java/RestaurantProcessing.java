public interface RestaurantProcessing {
    //Administrator

    /**
     * @pre menuItem != null
     * @post menuItem.getName().compareTo(menuItem@pre.getName()) == 0
     */
    void createItem(MenuItem menuItem);

    /**
     * @pre menuItem != null
     */
    void deleteItem(MenuItem menuItem);

    /**
     * @pre menuItem != null
     */
    void editItem(MenuItem menuItem);


    //Waiter

    /**
     * @pre order != null
     */
    void createOrder(Order order);

    /**
     * @pre order != null
     * @post @return >= 0
     */
    int computePrice(Order order);

    /**
     * @pre order != null
     */
    void generateBill(Order order);
}
