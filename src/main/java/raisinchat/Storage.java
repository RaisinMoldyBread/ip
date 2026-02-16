package raisinchat;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import raisinchat.exceptions.RaisinChatException;
import raisinchat.task.Deadline;
import raisinchat.task.Event;
import raisinchat.task.Task;
import raisinchat.task.TaskList;
import raisinchat.task.Todo;

/**
 * Persists tasks to disk and reconstructs them from a text database.
 */
public class Storage {

    private final String file;

    /**
     * Creates a storage handler targeting the given file path.
     *
     * @param filePath path to the task database file
     */
    public Storage(String filePath) {
        this.file = filePath;
    }

    /**
     * Loads tasks from the database file, creating it if missing.
     *
     * @return list of reconstructed tasks (empty if no tasks exist)
     * @throws RaisinChatException if the file contents are malformed or cannot be parsed
     */
    public ArrayList<Task> load() throws RaisinChatException {
        File taskFile = new File(this.file);

        try {
            File parentDir = taskFile.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs();
            }

            if (!taskFile.exists()) {
                taskFile.createNewFile();
                return new ArrayList<>(); // nothing to read yet
            }
            ArrayList<Task> listOfTask;
            try (Scanner fileReader = new Scanner(taskFile)) {
                listOfTask = new ArrayList<>();
                while (fileReader.hasNextLine()) {
                    String data = fileReader.nextLine();
                    String[] record = data.split("\\|");

                    if (record.length < 3) {
                        throw new RaisinChatException("Our data is corrupted! Resetting database :(");
                    }
                    switch (record[0].trim()) {
                    case "T":
                        listOfTask.add(new Todo(
                                record[2].trim(),
                                record[1].trim().equals("1")));
                        break;

                    case "D":
                        listOfTask.add(new Deadline(
                                record[2].trim(),
                                record[1].trim().equals("1"),
                                LocalDateTime.parse(record[3].trim())));
                        break;

                    case "E":
                        String[] times = record[3].split("->");
                        listOfTask.add(new Event(
                                record[2].trim(),
                                record[1].trim().equals("1"),
                                LocalDateTime.parse(times[0].trim()),
                                LocalDateTime.parse(times[1].trim())));
                        break;

                    default:
                        throw new RaisinChatException("Invalid data format! Resetting database :(");
                    }
                }
            }
            return listOfTask;
        } catch (IOException | DateTimeParseException e) {
            throw new RaisinChatException("Our data is corrupted! Resetting database :(");
        }
    }

    /**
     * Writes the current task list to the database file.
     *
     * @param tasks list of current tasks before program exits
     * @return {@code true} if the file was written successfully; {@code false} otherwise
     */
    public boolean save(TaskList tasks) {
        assert tasks != null : "TaskList passed to Storage.save() must not be null";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (int i = 0; i < tasks.size(); i++) {
                bw.write(tasks.getTask(i).getFullString());
                if (i < tasks.size() - 1) {
                    bw.newLine();
                }
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
