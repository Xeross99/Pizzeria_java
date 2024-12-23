import java.util.ArrayList;
import java.util.List;

public class PizzaBuilder implements IPizzaBuilder {
    List<String> ingredients = new ArrayList<>();
    int price = 0;
    int size = 0;

    @Override
    public void AddIngredient(String ingredient) {
        this.ingredients.add(ingredient);
    }

    @Override
    public void RemoveIngredient(String ingredient) {
        this.ingredients.remove(ingredients.indexOf(ingredient));
    }

    @Override
    public void SetPizzaSize(int size) {
        this.size = size;
    }

    @Override
    public void RecalculatePriceForCustomPizza() {
        int price = 0;
        int numberOfIngredients = ingredients.size();
        int costForExtraIngredient = 5;
        switch (size) {
            case 25:
                price = 30;
                if (numberOfIngredients > 3) {
                    price = price + costForExtraIngredient * (numberOfIngredients - 3);
                }
                break;

            case 30:
                price = 35;
                if (numberOfIngredients > 4) {
                    price = price + costForExtraIngredient * (numberOfIngredients - 4);
                }
                break;

            case 45:
                price = 40;
                if (numberOfIngredients > 4) {
                    price = price + costForExtraIngredient * (numberOfIngredients - 4);
                }
                break;

            default:
                break;
        }
        this.price = price;
    }

    public Pizza GetPizza() {
        return new Pizza(ingredients, price, size);
    }
}