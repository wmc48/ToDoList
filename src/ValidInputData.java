import java.util.Scanner;

public abstract class ValidInputData {
    public int validInt(Scanner scanner){
        int value;
        while (true) {
            if (scanner.hasNextInt()) {
                value = scanner.nextInt();
                scanner.nextLine();
                break;
            } else {
                System.out.println("Nieprawidłowy format. Wprowadź liczbę całkowitą.");
                scanner.nextLine();
            }
        }
        return value;
    }
}
