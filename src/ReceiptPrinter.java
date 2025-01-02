import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReceiptPrinter {
    public static void printReceiptToFile(Pizza pizza, String pizzaName, PizzaSize size, int price) {
        // Formatowanie daty zamówienia
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = now.format(formatter);

        String folderPath = "receipts";
        File folder = new File(folderPath);

        if (!folder.exists()) {
            boolean created = folder.mkdirs();
            if (!created) {
                System.out.println("Nie udało się utworzyć folderu na rachunki.");
                return;
            }
        }

        String fileName = folderPath + File.separator + "receipt_" + formattedDate + ".txt";

        StringBuilder receipt = new StringBuilder();
        receipt.append("============== Rachunek ==============\n");
        receipt.append("Data zamówienia: ").append(formattedDate).append("\n");
        receipt.append("-------------------------------------\n");
        receipt.append("Nazwa pizzy: ").append(pizzaName).append("\n");
        receipt.append("Rozmiar: ").append(size.getDisplayName()).append(" (").append(size.getDiameter()).append(" cm)\n");
        receipt.append("Składniki:\n");
        for (String ingredient : pizza.getIngredients()) {
            receipt.append("  - ").append(ingredient).append("\n");
        }
        receipt.append("-------------------------------------\n");
        receipt.append("Cena: ").append(price).append(" zł\n");
        receipt.append("=====================================\n");

        // Zapis do pliku
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(receipt.toString());
            System.out.println("Rachunek zapisany do pliku: " + fileName);
        } catch (IOException e) {
            System.out.println("Błąd podczas zapisu rachunku: " + e.getMessage());
        }
    }
}
