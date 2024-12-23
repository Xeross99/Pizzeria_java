public interface IPizzaBuilder {
    void AddIngredient(String ingredient);
    void RemoveIngredient(String ingredient);
    void SetPizzaSize(int size);
    void RecalculatePriceForCustomPizza();
}