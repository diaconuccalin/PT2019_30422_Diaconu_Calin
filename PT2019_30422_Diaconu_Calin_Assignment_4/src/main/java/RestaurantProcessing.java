public interface RestaurantProcessing {
    //Administrator
    void createItem();
    void deleteItem();
    void editItem();

    //Waiter
    void createOrder();
    void computePrice();
    void generateBill();
}
