import java.io.*;
import java.util.ArrayList;

public class TaskStorage {
    public static void save(ArrayList<Task> tasks) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("tasks.txt"));

        for (Task t : tasks) {
            writer.write(t.getId() + "$$" + t.getDescription() + "$$" + t.isCompleted());
            writer.newLine();
        }

        writer.close();

    }

    public static ArrayList<Task> load() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File("tasks.txt");
        if (!file.exists()) {
            return tasks;
        }

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split("$$");
            Task t = new Task(Integer.parseInt(parts[0]), parts[1]);
            if (Boolean.parseBoolean(parts[2])) {
                t.markCompleted();
            }
            tasks.add(t);
        }

        reader.close();
        return tasks;

    }
}
