public enum PizzaSize {
    SMALL("Mała", 25),
    MEDIUM("Średnia", 30),
    LARGE("Duża", 40);

    private final String displayName;
    private final int diameter;

    PizzaSize(String displayName, int diameter) {
        this.displayName = displayName;
        this.diameter = diameter;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getDiameter() {
        return diameter;
    }
    
    public static PizzaSize fromDiameter(int diameter) {
        for (PizzaSize size : PizzaSize.values()) {
            if (size.getDiameter() == diameter) {
                return size;
            }
        }
        throw new IllegalArgumentException("Nie znaleziono rozmiaru pizzy o średnicy: " + diameter);
    }
}
