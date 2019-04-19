import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CompositeProduct implements MenuItem, Serializable {
    private String name;
    private int price;

    private List<MenuItem> ingredients = new ArrayList<MenuItem>();

    public CompositeProduct(String name, int price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public int computePrice() {
        price = 0;

        for(MenuItem menuItem : ingredients) {
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
    }

    public void removeIngredient(MenuItem ingredient) {
        ingredients.remove(ingredient);
    }
}
