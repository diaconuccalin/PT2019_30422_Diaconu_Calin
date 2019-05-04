package BusinessLayer;

import java.io.Serializable;

public interface MenuItem extends Serializable {
    int computePrice();

    String getName();

    void setName(String name);

    void setPrice(int price);
}
