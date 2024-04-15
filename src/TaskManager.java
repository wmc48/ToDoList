import java.util.Scanner;

public class TaskManager extends ValidInputData {
    TaskService taskService = new TaskService();
    Scanner scanner = new Scanner(System.in);
    boolean exit = false;


    public void taskManagerMenu() {
        System.out.println("Witaj w aplikacji Listy ToDo!");

        while (!exit) {
            System.out.println("\nWybierz opcję:");
            System.out.println("1. Dodaj zadanie");
            System.out.println("2. Wyświetl zadania");
            System.out.println("3. Edytuj zadanie");
            System.out.println("4. Usuń zadanie");
            System.out.println("5. Wyjdź");

            int choice = validInt(scanner);
            switch (choice) {
                case 1:
                    taskService.addTask();
                    break;
                case 2:
                    taskService.displayTasks();
                    break;
                case 3:
                    taskService.editTask();
                    break;
                case 4:
                    taskService.deleteTask();
                    break;
                case 5:
                    exit = true;
                    System.out.println("Program zakończył działanie");
                    break;
                default:
                    System.out.println("Nieprawidłowy wybór. Spróbuj ponownie.");
                    break;
            }
        }
        scanner.close();
    }
}
