import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DatabaseManager {

    private static final String url = "jdbc:postgresql://localhost:5432/todolist";
    private static final String username = "postgres";
    private static final String password = "**";


    public void deleteTaskFromDatabase(int choice) {
        String sql = "DELETE FROM tasks WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, choice);// ustawiamy parametr zapytania na podstawie choice

            int rowsAffected = statement.executeUpdate();// wykonujemy sql
            if (rowsAffected > 0) { //jeśli sukces zapytania do DB
                System.out.println("Pozycja o ID " + choice + " została pomyślnie usunięta.");
            } else {
                System.out.println("Nie znaleziono pozycji o ID " + choice + ".");
            }
        } catch (SQLException e) {
            System.err.println("Błąd podczas usuwania pozycji: " + e.getMessage());
        }
    }

    public void addTaskToDatabase(Task task) {
        String sql = "INSERT INTO tasks (description, deadline, priority) VALUES (?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, task.getDescription());
            statement.setDate(2, java.sql.Date.valueOf(task.getDeadline()));
            statement.setInt(3, task.getPriority());

            int rowsAffected = statement.executeUpdate(); // wykonujemy sql
            if (rowsAffected > 0) { //jeśli sukces zapytania do DB
                System.out.println("Zadanie zostało dodane do bazy danych. Opis: " + task.getDescription() +
                        " deadline: " + task.getDeadline() + " priorytet: " + task.getPriority());
            } else {
                System.out.println("Dodawanie zadania nie powiodło się.");
            }
        } catch (SQLException e) {
            System.err.println("Błąd podczas dodawania zadania do bazy danych: " + e.getMessage());
        }
    }


    public List<Task> getAllTasks() {
        List<Task> tasks = new ArrayList<>();

        String sql = "SELECT * FROM tasks ORDER BY deadline";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String description = resultSet.getString("description");
                LocalDate deadline = resultSet.getDate("deadline").toLocalDate();
                int priority = resultSet.getInt("priority");

                Task task = new Task(id, description, deadline, priority);
                tasks.add(task);
            }
        } catch (SQLException e) {
            System.err.println("Błąd podczas pobierania danych: " + e.getMessage());
        }
        return tasks;
    }


    public void editTaskById(Task task) {
        String sql = "UPDATE tasks SET description = ?, deadline = ?, priority = ? WHERE id = ?";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            // ustawiamy nowe parametry
            statement.setString(1, task.getDescription());
            statement.setDate(2, Date.valueOf(task.getDeadline()));
            statement.setInt(3, task.getPriority());
            statement.setInt(4, task.getId());

            int rowsAffected = statement.executeUpdate(); // wykonujemy sql
            if (rowsAffected > 0) { //jeśli sukces zapytania do DB
                System.out.println("Pozycja o ID " + task.getId() + " została pomyślnie zaktualizowana.");
            } else {
                System.out.println("Nie znaleziono pozycji o ID " + task.getId() + ".");
            }
        } catch (SQLException e) {
            System.err.println("Błąd podczas aktualizowania pozycji: " + e.getMessage());
        }
    }
}
