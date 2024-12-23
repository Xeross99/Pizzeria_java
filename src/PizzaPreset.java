import java.util.List;

public enum PizzaPreset {
    MARGHERITA("Margherita", List.of("Sos pomidorowy", "Ser mozzarella", "Bazylia", "Oregano", "Oliwa z oliwek"),
            20, 25, 30),
    PEPPERONI("Pepperoni", List.of("Sos pomidorowy", "Ser mozzarella", "Pepperoni", "Oregano"),
            22, 27, 32),
    HAWAJSKA("Hawajska", List.of("Sos pomidorowy", "Ser mozzarella", "Szynka", "Ananas", "Oregano"),
            23, 28, 33),
    VEGETARIANA("Vegetariana",
            List.of("Sos pomidorowy", "Ser mozzarella", "Pieczarki", "Papryka", "Oliwki", "Cebula", "Kukurydza",
                    "Oregano"),
            21, 26, 31),
    MEAT_LOVERS("Meat Lovers",
            List.of("Sos pomidorowy", "Ser mozzarella", "Bekon", "Szynka", "Kurczak", "Salami", "Pepperoni", "Oregano"),
            25, 30, 35),
    DO_IT_YOURSELF("Zrób to sam", List.of(),
            30, 35, 40);

    private final String name;
    private final List<String> ingredients;
    private final int smallPrice;
    private final int mediumPrice;
    private final int largePrice;

    PizzaPreset(String name, List<String> ingredients, int smallPrice, int mediumPrice, int largePrice) {
        this.name = name;
        this.ingredients = ingredients;
        this.smallPrice = smallPrice;
        this.mediumPrice = mediumPrice;
        this.largePrice = largePrice;
    }

    public String getName() {
        return name;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public int getPrice(PizzaSize size) {
        return switch (size) {
            case SMALL -> smallPrice;
            case MEDIUM -> mediumPrice;
            case LARGE -> largePrice;
        };
    }
}
