import java.util.List;

public class Pizza {
    private final List<String> ingredients;
    private final int price;
    private final int size;

    public Pizza(List<String> ingredients, int price, int size) {
        this.ingredients = ingredients;
        this.price = price;
        this.size = size;
    }

    public int GetPrice() {
        return this.price;
    }

    public int GetSize() {
        return this.size;
    }

    public List<String> getIngredients() {
        return this.ingredients;
    }    
}