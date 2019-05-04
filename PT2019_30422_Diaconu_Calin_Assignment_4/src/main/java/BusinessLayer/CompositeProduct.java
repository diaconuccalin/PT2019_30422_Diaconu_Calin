package BusinessLayer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CompositeProduct implements MenuItem, Serializable {
    private String name;
    private int price;

    private List<MenuItem> ingredients = new ArrayList<>();

    public CompositeProduct(String name) {
        this.name = name;
        this.price = computePrice();
    }

    @Override
    public int computePrice() {
        price = 0;

        for (MenuItem menuItem : ingredients) {
            menuItem.setPrice(Restaurant.getItem(menuItem.getName()).computePrice());
            price += menuItem.computePrice();
        }

        return price;
    }

    @Override
    public String getName() {
        return name;
    }

    public void addIngredient(MenuItem ingredient) {
        ingredients.add(ingredient);
        this.price = computePrice();
    }

    public List<MenuItem> getIngredients() {
        return ingredients;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setPrice(int price) {
        this.price = price;
    }
}
