
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskService extends ValidInputData {
    private final ArrayList<Task> tasks;
    Scanner scanner = new Scanner(System.in);


    public TaskService() {
        tasks = new ArrayList<>();
    }

    public void deleteTask() {
        displayTasks();
        System.out.println("Wybierz zadanie które chcesz usunąć: ");
        int choice = validInt(scanner);
        if (choice <= tasks.size() && choice > 0) {
            System.out.println("Usunięto zadanie " + choice + " Opis: " + tasks.get(choice - 1).getDescription() +
                    " Deadline: " + tasks.get(choice - 1).getDeadlinie() + " Priorytet: " + tasks.get(choice - 1).getPriority());
            tasks.remove(choice - 1);
            displayTasks();
        } else {
            System.out.println("Wybrano nieprawidłowe zadanie");
        }
    }

    public void editTask() {
        displayTasks();
        System.out.println("Wybierz zadanie które chcesz edytować: ");
        int choice = validInt(scanner);
        if (choice <= tasks.size() && choice > 0) {
            System.out.println("Edytujesz zadanie " + choice + " Opis: " + tasks.get(choice - 1).getDescription() +
                    " Deadline: " + tasks.get(choice - 1).getDeadlinie() + " Priorytet: " + tasks.get(choice - 1).getPriority());
            String newTaskDescription = addNewDescription();
            LocalDate deadlinie = addTaskDeadline();
            int newTaskPriority = addTaskPriority();

            Task task = new Task(newTaskDescription, deadlinie, newTaskPriority);
            tasks.set(choice - 1, task);
            displayTasks();
        } else {
            System.out.println("Wybrano nieprawidłowe zadanie");
        }
    }

    public void addTask() {
        String newTaskDescription = addNewDescription();
        LocalDate deadlinie = addTaskDeadline();
        int newTaskPriority = addTaskPriority();

        Task task = new Task(newTaskDescription, deadlinie, newTaskPriority);
        tasks.add(task);
        System.out.println("Zadanie dodane: " + task);
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


    public void displayTasks() {
        if (tasks.isEmpty()) {
            System.out.println("Lista zadań jest pusta.");
        } else {
            System.out.println("Obecna lista zadań:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". Deadline: " + tasks.get(i).getDeadlinie() +
                        ".\t Priorytet: " + tasks.get(i).getPriority() + "|\t Opis: " + tasks.get(i).getDescription());
            }
            System.out.println("wielkość tablicy " + tasks.size());
        }
    }
}