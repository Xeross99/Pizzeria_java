public enum PizzaIngredient {
     // Podstawowe składniki
    CHEESE("Ser"),
    TOMATO_SAUCE("Sos pomidorowy"),
    DOUGH("Ciasto"),
    // Mięso
    PEPPERONI("Pepperoni"),
    HAM("Szynka"),
    BACON("Bekon"),
    CHICKEN("Kurczak"),
    // Warzywa
    MUSHROOMS("Pieczarki"),
    ONIONS("Cebula"),
    OLIVES("Oliwki"),
    BELL_PEPPERS("Papryka"),
    SPINACH("Szpinak"),
    CORN("Kukurydza"),
    // Inne
    PINEAPPLE("Ananas"),
    JALAPENO("Jalapeño"),
    GARLIC("Czosnek"),
    OREGANO("Oregano");

    private final String displayName;

    PizzaIngredient(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
