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

    public Product(int idproduct) {
        this.idproduct = idproduct;
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

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDistributor(String distributor) {
        this.distributor = distributor;
    }

    public void setIdproduct(int idproduct) {
        this.idproduct = idproduct;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
