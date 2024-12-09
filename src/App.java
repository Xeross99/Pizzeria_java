public class App {
    public static void main(String[] args) throws Exception {
        Pizzeria pizzeria = new Pizzeria("Domino's Pizza", 10);
        pizzeria.displayInfo();
        
        pizzeria.updateOrders(11);
        pizzeria.displayInfo();
    }
}
