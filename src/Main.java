import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        ArrayList<Task> tasks = TaskStorage.load();
        TaskManager manager = new TaskManager();
        tasks.forEach(t -> manager.addTask(t));
        manager.alignIds();

        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add Task");
            System.out.println("2. List Tasks");
            System.out.println("3. Complete Task");
            System.out.println("4. Delete Task");
            System.out.println("5. Exit");

            try {
                int choice = sc.nextInt();
                sc.nextLine();

                if (choice == 1) {
                    System.out.println("Task: ");
                    String desc = sc.nextLine();
                    manager.addTask(desc);
                } else if (choice == 2) {
                    manager.listTasks();
                } else if (choice == 3) {
                    System.out.println("Select a task to complete: ");
                    manager.listTasks();
                    System.out.println("Task ID: ");
                    int id = sc.nextInt();
                    manager.completeTask(id);
                } else if (choice == 4) {
                    System.out.println("Select a task to delete: ");
                    manager.listTasks();
                    System.out.println("Task ID: ");
                    int id = sc.nextInt();
                    manager.deleteTask(id);
                } else if (choice == 5) {
                    break;
                }
            } catch (Exception e) {
                sc.nextLine();
                System.out.println("Invalid input, please enter a number.");
                continue;
            }
            TaskStorage.save(manager.getTasks());
        }

        sc.close();

    }
}
