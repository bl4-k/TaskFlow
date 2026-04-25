import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        TaskManager manager = new TaskManager();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Add Task");
            System.out.println("2. List Tasks");
            System.out.println("3. Complete Task");
            System.out.println("4. Delete Task");
            System.out.println("5. Exit");

            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                System.out.println("Task: ");
                String desc = sc.nextLine();
                manager.addTask(desc);

            } else if (choice == 2) {
                manager.listTasks();

            } else if (choice == 3) {
                System.out.println("Task ID: ");
                int id = sc.nextInt();
                manager.completeTask(id);
            } else if (choice == 4) {
                System.out.println("Task ID: ");
                int id = sc.nextInt();
                manager.deleteTask(id);
            } else if (choice == 5) {
                break;
            }
        }

        sc.close();

    }
}
