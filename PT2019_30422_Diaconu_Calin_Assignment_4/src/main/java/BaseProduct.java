public class BaseProduct implements MenuItem {
    private int id;
    private String name;
    private int price;

    public BaseProduct(String name, int price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public int computePrice() {
        return price;
    }
}
