
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class TaskService extends ValidInputData {
    private List<Task> tasks;
    Scanner scanner = new Scanner(System.in);
    DatabaseManager databaseManager = new DatabaseManager();

    public void deleteTask() {
        displayTasks();
        System.out.println("Wybierz zadanie które chcesz usunąć: ");
        int choice = validInt(scanner);
        int taskID = tasks.get(choice - 1).getId();

        System.out.println("wybrano zadanie " + choice + " o nr id w bd: " + taskID );
        databaseManager.deleteTaskFromDatabase(taskID);
    }


    public void editTask() {
        displayTasks();
        System.out.println("Wybierz zadanie które chcesz edytować: ");
        int choice = validInt(scanner);
        System.out.println("Edytujesz zadanie " + choice);

        String newDescription = addNewDescription();
        LocalDate newDeadlinie = addTaskDeadline();
        int newPriority = addTaskPriority();
        int taskID = tasks.get(choice - 1).getId();

        Task task = new Task(taskID, newDescription, newDeadlinie, newPriority);
        databaseManager.editTaskById(task);

    }

    public void addTask() {
        String description = addNewDescription();
        LocalDate deadline = addTaskDeadline();
        int priority = addTaskPriority();
        Task task = new Task(0, description, deadline, priority); //id może byc 0 jest w bd jest autoincrement
        databaseManager.addTaskToDatabase(task);
    }


    public void displayTasks() {
        tasks = databaseManager.getAllTasks();

        System.out.println("Lista wszystkich zadań:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            System.out.println("Zadanie " + (i + 1) + ": \t" + task);
        }
    }

    private LocalDate addTaskDeadline() {
        boolean validDate;
        LocalDate deadlinie = null;
        do {
            System.out.println("Podaj deadline nowego zadania(dd.MM.yyyy):");
            String newDeadlineTask = scanner.nextLine();
            try {
                deadlinie = LocalDate.parse(newDeadlineTask, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
                validDate = true;
            } catch (DateTimeParseException e) {
                System.out.println("Nieprawidłowy format daty. Spróbuj jeszcze raz.");
                validDate = false;
            }
        } while (!validDate);
        return deadlinie;
    }

    private int addTaskPriority() {

        System.out.println("Podaj zakres priorytetu (1-10): ");
        do {
            int priority = validInt(scanner);
            if (priority > 0 && priority <= 10) {
                return priority;
            } else {
                System.out.println("Podany zakres priorytetu powinien mieścić się w zakresie 1-10");
            }
        } while (true);
    }

    public String addNewDescription() {
        System.out.println("Podaj treść nowego zadania:");
        return scanner.nextLine();

    }
}