import java.util.Scanner;

public class App {
    public static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void print(String text) {
        System.out.println(text);
    }

    public static Pizza CustomPizza(){
        PizzaBuilder builder = new PizzaBuilder();
        PizzaIngredient[] ingredients = PizzaIngredient.values();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            clearConsole();
            print("Podaj nr składnika jaki chcesz dodać do pizzy:");
            for (int i = 0; i < ingredients.length; i++) {
                print("Nr: " + i + ", Składnik: " + ingredients[i].getDisplayName());
            }
            String input = scanner.nextLine();

            try {
                int number = Integer.parseInt(input);
                builder.AddIngredient(ingredients[number].getDisplayName());
            } catch (NumberFormatException e) {
                print("Nie udało się dodać skłądnika, spróbuj jeszcze raz.");
            }
            print("Czy chcesz dodać jeszcze jakiś składnik? T/n");
            input = scanner.nextLine();
            if (input.equals("") || input.equalsIgnoreCase("T")) {
                break;
            }
        }
        scanner.close();
        return builder.GetPizza();
    }

    public static void main(String[] args) throws Exception {
        Pizzeria pizzeria = new Pizzeria("Domino's Pizza", 10);

        pizzeria.displayInfo();

        pizzeria.updateOrders(11);
        pizzeria.displayInfo();
        print(String.valueOf(CustomPizza().GetListOfIngredients().size()));
    }
}
