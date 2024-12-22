import java.util.ArrayList;
import java.util.List;

public class PizzaBuilder implements IPizzaBuilder {
    List<String> ingredients = new ArrayList<>();
    int price = 0;

    @Override
    public void AddIngredient(String ingredient) {
        ingredients.add(ingredient);
    }

    @Override
    public void RemoveIngredient(String ingredient) {
        ingredients.remove(ingredients.indexOf(ingredient));
    }

    public Pizza GetPizza() {
        return new Pizza(ingredients, price);
    }
}