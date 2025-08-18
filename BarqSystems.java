public class BarqSystems {
    // ANSI escape codes for colors
    public static final String BLUE = "\u001B[34m";
    public static final String GRAY = "\u001B[37m";
    public static final String RESET = "\u001B[0m";

    public static void main(String[] args) {
        // Print Header
        System.out.println(BLUE + "==============================");
        System.out.println("        BARQ SYSTEMS");
        System.out.println("==============================" + RESET);

        // Sub-header
        System.out.println(GRAY + "\n--- DEPARTMENT ---" + RESET);

        // Options
        System.out.println("1. DevOps");
        System.out.println("2. Data Analysis");
        System.out.println("3. Security");

        // End note
        System.out.println(BLUE + "\nThank you for choosing BARQ SYSTEMS!" + RESET);
    }
}

