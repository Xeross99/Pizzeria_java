import java.util.List;

public class Pizza {
    private final List<String> ingredients;
    private final int price;

    public Pizza(List<String> ingredients, int price) {
        this.ingredients = ingredients;
        this.price = price;
    }

    public int GetPrice() {
        return price;
    }

    public List<String> GetListOfIngredients() {
        return ingredients;
    }
}