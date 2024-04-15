import java.time.LocalDate;

public class Task {
    private final int id;
    private final String description;
    private final LocalDate deadline;
    private final int priority;

    public Task(int id, String description, LocalDate deadline, int priority) {
        this.id = id;
        this.description = description;
        this.deadline = deadline;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return String.format("Opis: %-30s | Deadline: %s | Priorytet: %2d | ID w bd: %3d",
                description, deadline, priority, id);
    }
}
