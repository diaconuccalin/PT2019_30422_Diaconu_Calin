package model;

public class Product {
    private int idproduct;
    private String name;
    private int stock;
    private String distributor;
    private int price;

    public Product(String name, int stock, String distributor, int price) {
        this.name = name;
        this.stock = stock;
        this.distributor = distributor;
        this.price = price;
    }

    public Product(int idProduct, String name, int stock, String distributor, int price) {
        this.idproduct = idProduct;
        this.name = name;
        this.stock = stock;
        this.distributor = distributor;
        this.price = price;
    }

    public int getIdproduct() {
        return idproduct;
    }

    public String getName() {
        return name;
    }

    public int getStock() {
        return stock;
    }

    public String getDistributor() {
        return distributor;
    }

    public int getPrice() {
        return price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
