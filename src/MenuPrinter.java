public class MenuPrinter {
    private static String lineSeparator = "-";

    private static void updateLineSeparator(String text) {
        String[] lines = text.split("\n");
        int maxLength = lineSeparator.length();
        for (String line : lines) {
            maxLength = Math.max(maxLength, line.length() + 4);
        }
        lineSeparator = "-".repeat(maxLength);
    }

    public static void printHeaderWithContent(String header, String content) {
        String combinedText = header + "\n" + content;
        updateLineSeparator(combinedText);

        System.out.println(lineSeparator);
        int padding = (lineSeparator.length() - header.length()) / 2;
        String paddedHeader = " ".repeat(padding) + header + " ".repeat(padding);
        if (paddedHeader.length() < lineSeparator.length()) {
            paddedHeader += " ";
        }
        System.out.println(paddedHeader);
        System.out.println(lineSeparator + "\n");
        System.out.println(content);
        System.out.println(lineSeparator);
    }

    public static void printError(String errorMessage) {
        String errorText = "!!! Błąd: " + errorMessage + " !!!";
        System.out.println(errorText);
    }

    public static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
