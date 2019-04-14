package model;

public class Product {
    private int idproduct;
    private String name;
    private int stock;
    private int distributorid;
    private int price;

    public Product(String name, int stock, int distributor, int price) {
        this.name = name;
        this.stock = stock;
        this.distributorid = distributor;
        this.price = price;
    }

    public Product(int idProduct, String name, int stock, int distributor, int price) {
        this.idproduct = idProduct;
        this.name = name;
        this.stock = stock;
        this.distributorid = distributor;
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

    public int getDistributorid() {
        return distributorid;
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

    public void setDistributorid(int distributor) {
        this.distributorid = distributor;
    }

    public void setIdproduct(int idproduct) {
        this.idproduct = idproduct;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
