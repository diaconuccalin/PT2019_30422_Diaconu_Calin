package BusinessLayer;

import java.io.Serializable;

public class BaseProduct implements MenuItem, Serializable {
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

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
