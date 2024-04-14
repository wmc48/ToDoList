import java.time.LocalDate;

public class Task {
    private int id = 0;
    private String description;
    private LocalDate deadlinie;
    private int priority;



    public Task(String description, LocalDate deadlinie, int priority) {
        this.id = id++;
        this.description = description;
        this.deadlinie = deadlinie;
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDeadlinie() {
        return deadlinie;
    }

    public int getPriority() {
        return priority;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Task{" +
                "description='" + description + '\'' +
                ", deadlinie=" + deadlinie +
                ", priority=" + priority +
                '}';
    }
}
