import java.util.ArrayList;

public class TaskManager {
    public ArrayList<Task> tasks = new ArrayList<>();
    private int nextId = 1;

    public void addTask(String description) {
        Task newTask = new Task(nextId++, description);
        tasks.add(newTask);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void listTasks() {
        tasks.forEach((t) -> System.out.println(t));
    }

    public void completeTask(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                task.markCompleted();
            }
        }
    }

    public void deleteTask(int id) {
        tasks.removeIf(t -> t.getId() == id);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void alignIds() {
        nextId = tasks.size() + 1;
    }
}
