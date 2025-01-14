import java.util.Scanner;

public class App {
    public static Pizza CustomPizza(Scanner scanner) {
        PizzaBuilder builder = new PizzaBuilder();
        PizzaIngredient[] ingredients = PizzaIngredient.values();
        PizzaSize[] sizes = PizzaSize.values();
    
        while (true) {
            StringBuilder options = new StringBuilder();
            for (int i = 0; i < sizes.length; i++) {
                options.append("Nr: ").append(i).append(", rozmiar: ")
                       .append(sizes[i].getDisplayName())
                       .append(", średnica: ").append(sizes[i].getDiameter()).append("\n");
            }
            MenuPrinter.printHeaderWithContent("Podaj rozmiar pizzy jaką chcesz zamówić:", options.toString());
    
            String input = scanner.nextLine();
    
            try {
                builder.SetPizzaSize(sizes[Integer.parseInt(input)].getDiameter());
                break;
            } catch (NumberFormatException e) {
                MenuPrinter.printError("Nie udało się dodać składnika, spróbuj jeszcze raz.");
            }
        }
    
        while (true) {
            MenuPrinter.clearConsole();
            StringBuilder options = new StringBuilder();
            for (int i = 0; i < ingredients.length; i++) {
                options.append("Nr: ").append(i).append(", Składnik: ")
                       .append(ingredients[i].getDisplayName()).append("\n");
            }
            MenuPrinter.printHeaderWithContent("Podaj nr składnika jaki chcesz dodać do pizzy:", options.toString());
    
            String input = scanner.nextLine();
    
            try {
                builder.AddIngredient(ingredients[Integer.parseInt(input)].getDisplayName());
            } catch (NumberFormatException e) {
                MenuPrinter.printError("Nie udało się dodać składnika, spróbuj jeszcze raz.");
            }

            System.out.println("Czy chcesz dodać jeszcze jakiś składnik? T/n");
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
                MenuPrinter.printError("Podana wartość była błędna.");
            }
        }
        return decision;
    }

    public static int PickPizza(String[] pizzas, Scanner scanner) {
        int pizzaNumber = 0;
        while (true) {
            MenuPrinter.clearConsole();
            StringBuilder options = new StringBuilder();
            for (int i = 0; i < pizzas.length; i++) {
                options.append(i).append(" -> Pizza: ").append(pizzas[i]).append("\n\n");
            }
            MenuPrinter.printHeaderWithContent("Wybierz pizzę, którą chcesz zamówić:", options.toString());
            pizzaNumber = DecisionBlock(scanner);
            if (pizzaNumber >= 0 && pizzaNumber < pizzas.length) {
                break;
            }
        }
        return pizzaNumber;
    }

    public static int PickPizzaSize(Scanner scanner) {
        PizzaSize[] sizes = PizzaSize.values();

        while (true) {
            StringBuilder options = new StringBuilder();
            for (int i = 0; i < sizes.length; i++) {
                options.append(i).append(" -> rozmiar: ").append(sizes[i].getDisplayName())
                       .append(", średnica: ").append(sizes[i].getDiameter()).append(" cm\n");
            }
            MenuPrinter.printHeaderWithContent("Podaj rozmiar pizzy jaką chcesz zamówić:", options.toString());

            String input = scanner.nextLine();

            int selectedIndex = Integer.parseInt(input);
            if (selectedIndex >= 0 && selectedIndex < sizes.length) {
                return selectedIndex;
            }

        }
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
                "     Ceny: Mała - 20 zł, Średnia - 25 zł, Duża - 30 zł",

                "Pepperoni: Sos pomidorowy, Ser mozzarella, Pepperoni, Oregano\n" +
                "     Ceny: Mała - 22 zł, Średnia - 27 zł, Duża - 32 zł",

                "Hawajska: Sos pomidorowy, Ser mozzarella, Szynka, Ananas, Oregano\n" +
                "     Ceny: Mała - 23 zł, Średnia - 28 zł, Duża - 33 zł",

                "Vegetariana: Sos pomidorowy, Ser mozzarella, Pieczarki, Papryka, Oliwki, Cebula, Kukurydza, Oregano\n" +
                "     Ceny: Mała - 21 zł, Średnia - 26 zł, Duża - 31 zł",

                "Meat Lovers: Sos pomidorowy, Ser mozzarella, Bekon, Szynka, Kurczak, Salami, Pepperoni, Oregano\n" +
                "     Ceny: Mała - 25 zł, Średnia - 30 zł, Duża - 35 zł",
                        
                "Zrób to sam: Możesz wybrać 3 składniki dla małej, 4 dla średniej, 5 dla dużej a każdy kolejny kosztuje 5 zł\n" +
                "     Ceny: Mała - 30 zł, Średnia - 35 zł, Duża - 40 zł"
        };

        while (true) {
            StringBuilder prompt = new StringBuilder();
            MenuPrinter.clearConsole();
            prompt.append("Jeżeli jesteś zainteresowany złożeniem zamówinia:\n\n")
                  .append("1 -> zobacz menu\n")
                  .append("2 -> opuść restaurację.");
            MenuPrinter.printHeaderWithContent("Witaj w naszej Pizzerii!", prompt.toString());

            int decision = 0;
            while (true) {
                String input = scanner.nextLine();
                try {
                    decision = Integer.parseInt(input);
                    break;
                } catch (NumberFormatException e) {
                    MenuPrinter.printError("Podana wartość była błędna. Wprowadź liczbę 1 lub 2.");
                }
            }

            if (decision == 1) {
                int pickedPizza = PickPizza(pizzas, scanner);
                Pizza pizza = null;
                if (pickedPizza < 5) {
                    pizza = createPizzaFromPreset(presets[pickedPizza], sizes[PickPizzaSize(scanner)]);
                } else {
                    pizza = CustomPizza(scanner);
                }
                ReceiptPrinter.printReceiptToFile(pizza, presets[pickedPizza].getName(), PizzaSize.fromDiameter(pizza.GetSize()), pizza.GetPrice());

                MenuPrinter.clearConsole();
                MenuPrinter.printHeaderWithContent(
                    "Dziękujemy za złożenie zamówienia!", 
                    "Twój rachunek znajdziesz na dysku.\n"
                );

                System.out.println("Naciśnij Enter, aby kontynuować...");
                scanner.nextLine();
            } else if (decision == 2) {
                scanner.close();
                System.out.println("Program zakończył działanie.");
                break;
            } else {
                System.out.println("Nieprawidłowy wybór. Wybierz 1, aby zamówić pizzę, lub 0, aby opuścić restaurację.");
            }
        }
    }
}
