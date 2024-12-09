public class Pizzeria {
  private String name;
  private int numberOfOrders;

  public Pizzeria(String name, int numberOfOrders) {
      this.name = name;
      this.numberOfOrders = numberOfOrders;
  }

  public void displayInfo() {
      System.out.println("Witaj w pizzerii: " + name + " !");
      System.out.println("Liczba zamówień: " + numberOfOrders);
  }

  public void updateOrders(int newOrders) {
      this.numberOfOrders = newOrders;
      System.out.println("Updated number of orders to: " + numberOfOrders);
  }
}