import java.util.Scanner;

public class App {
    public static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void print(String text) {
        System.out.println(text);
    }

    public static Pizza CustomPizza(Scanner scanner) {
        PizzaBuilder builder = new PizzaBuilder();
        PizzaIngredient[] ingredients = PizzaIngredient.values();
        PizzaSize[] sizes = PizzaSize.values();
        while (true) {
            print("Podaj rozmiar pizzy jaką chcesz zamówić:");

            for (int i = 0; i < sizes.length; i++) {
                print("Nr: " + i + ", rozmiar: " + sizes[i].getDisplayName() + ", średnica: " + sizes[i].getDiameter());
            }

            String input = scanner.nextLine();

            try {
                builder.SetPizzaSize(sizes[Integer.parseInt(input)].getDiameter());
                break;
            } catch (NumberFormatException e) {
                print("Nie udało się dodać skłądnika, spróbuj jeszcze raz.");
            }
        }
        while (true) {
            clearConsole();
            print("Podaj nr składnika jaki chcesz dodać do pizzy:");
            for (int i = 0; i < ingredients.length; i++) {
                print("Nr: " + i + ", Składnik: " + ingredients[i].getDisplayName());
            }
            String input = scanner.nextLine();

            try {
                builder.AddIngredient(ingredients[Integer.parseInt(input)].getDisplayName());
            } catch (NumberFormatException e) {
                print("Nie udało się dodać skłądnika, spróbuj jeszcze raz.");
            }
            print("Czy chcesz dodać jeszcze jakiś składnik? T/n");
            input = scanner.nextLine();
            if (input.equalsIgnoreCase("n")) {
                break;
            }
        }
        builder.RecalculatePriceForCustomPizza();
        return builder.GetPizza();
    }

    public static int DecisionBlock(Scanner scanner) {
        int decision = 0;
        while (true) {
            String input = scanner.nextLine();
            try {
                decision = Integer.parseInt(input);
                break;
            } catch (NumberFormatException e) {
                print("Podana wartość była błędna.");
            }
        }
        return decision;
    }

    public static int PickPizza(String[] pizzas, Scanner scanner) {
        int pizzaNumber = 0;
        while (true) {
            clearConsole();
            print("Wybierz pizze, którą chcesz zamówić");
            for (int i = 0; i < pizzas.length; i++) {
                print("Nr: " + i + ", Pizza: " + pizzas[i]);
            }
            pizzaNumber = DecisionBlock(scanner);
            if (pizzaNumber >= 0 && pizzaNumber < pizzas.length) {
                break;
            }
        }
        return pizzaNumber;
    }

    public static int PickPizzaSize(Scanner scanner) {
        PizzaSize[] sizes = PizzaSize.values();
        int size = 0;
        while (true) {
            print("Podaj rozmiar pizzy jaką chcesz zamówić:");

            for (int i = 0; i < sizes.length; i++) {
                print("Nr: " + i + ", rozmiar: " + sizes[i].getDisplayName() + ", średnica: "
                        + sizes[i].getDiameter() + " cm");
            }

            String input = scanner.nextLine();

            try {
                sizes[Integer.parseInt(input)].getDiameter();
                break;
            } catch (NumberFormatException e) {
                print("Nie udało się wybrać rozmiaru.");
            }
        }
        return size;
    }

    public static Pizza createPizzaFromPreset(PizzaPreset preset, PizzaSize size) {
        PizzaBuilder builder = new PizzaBuilder();
        for (String ingredient : preset.getIngredients()) {
            builder.AddIngredient(ingredient);
        }
        builder.SetPizzaSize(size.getDiameter());
        builder.price = preset.getPrice(size);
        return builder.GetPizza();
    }

    public static void main(String[] args) throws Exception {
        PizzaPreset[] presets = PizzaPreset.values();
        PizzaSize[] sizes = PizzaSize.values();
        Scanner scanner = new Scanner(System.in);
        String[] pizzas = {
                "Margherita: Sos pomidorowy, Ser mozzarella, Bazylia, Oregano, Oliwa z oliwek\n" +
                        "Ceny: Mała - 20 zł, Średnia - 25 zł, Duża - 30 zł",

                "Pepperoni: Sos pomidorowy, Ser mozzarella, Pepperoni, Oregano\n" +
                        "Ceny: Mała - 22 zł, Średnia - 27 zł, Duża - 32 zł",

                "Hawajska: Sos pomidorowy, Ser mozzarella, Szynka, Ananas, Oregano\n" +
                        "Ceny: Mała - 23 zł, Średnia - 28 zł, Duża - 33 zł",

                "Vegetariana: Sos pomidorowy, Ser mozzarella, Pieczarki, Papryka, Oliwki, Cebula, Kukurydza, Oregano\n"
                        +
                        "Ceny: Mała - 21 zł, Średnia - 26 zł, Duża - 31 zł",

                "Meat Lovers: Sos pomidorowy, Ser mozzarella, Bekon, Szynka, Kurczak, Salami, Pepperoni, Oregano\n" +
                        "Ceny: Mała - 25 zł, Średnia - 30 zł, Duża - 35 zł",
                        
                "Zrób to sam: Możesz wybrać 3 składniki dla małej, 4 dla średniej, 5 dla dużej a kazdy kolejny kosztuje 5zł\n" +
                "Ceny: Mała - 30 zł, Średnia - 35 zł, Duża - 40 zł"
        };
        clearConsole();
        print("Witaj w naszej Pizzerii, jeżeli jesteś zaintersowany złożeniem zamówinia naciśnij 1 aby zobaczyć menu lub 0 Aby opuścić nasza restaurację.");
        if (DecisionBlock(scanner) == 1) {
            int pickedPizza = PickPizza(pizzas, scanner);
            Pizza pizza = null;
            if (pickedPizza < 5) {
                pizza = createPizzaFromPreset(presets[pickedPizza], sizes[PickPizzaSize(scanner)]);
            } else {
                pizza = CustomPizza(scanner);
            }
            ReceiptPrinter.printReceiptToFile(pizza, presets[pickedPizza].getName(), PizzaSize.fromDiameter(pizza.GetSize()), pizza.GetPrice());
            clearConsole();
            print("Dziękujemy za złożenie zamówienia twój rachunek znajdziesz na dysku.");
        }
        scanner.close();
    }
}
