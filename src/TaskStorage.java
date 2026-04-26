import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class TaskStorage {
    public static void save(ArrayList<Task> tasks) throws IOException {

        try {
            Path folderPath = getAppDataPath();
            Path taskFile = folderPath.resolve("tasks.txt");

            Files.createDirectories(folderPath);
            List<String> lines = new ArrayList<>();

            for (Task t : tasks) {
                lines.add(t.getDescription() + "," + t.isCompleted());
            }
            Files.write(taskFile,
                    lines,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.WRITE,
                    StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            Path folderPath = getAppDataPath();
            System.err.println("FAILED TO WRITE TO: " + folderPath.toAbsolutePath());
        }

    }

    public static ArrayList<Task> load() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        Path taskFile = getAppDataPath().resolve("tasks.txt");

        if (!Files.exists(taskFile)) {
            return tasks;
        }

        List<String> lines = Files.readAllLines(taskFile);
        int id = 1;
        for (String line : lines) {
            int lastComma = line.lastIndexOf(",");

            String description = line.substring(0, lastComma);
            boolean completed = Boolean.parseBoolean(line.substring(lastComma + 1));

            Task t = new Task(id, description);

            if (completed) {
                t.markCompleted();
            }
            tasks.add(t);
            id++;
        }

        return tasks;

    }

    private static Path getAppDataPath() {
        String os = System.getProperty("os.name").toLowerCase();
        String userHome = System.getProperty("user.home");
        Path path;

        if (os.contains("win")) {
            path = Paths.get(System.getenv("APPDATA"), "TaskFlow");
        } else if (os.contains("mac")) {
            path = Paths.get(userHome, "Library", "Application Support", "TaskFlow");
        } else {
            path = Paths.get(userHome, ".taskflow");
        }

        return path;
    }
}
