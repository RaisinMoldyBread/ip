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
 * Abstraction of storing and loading function from a text task list database
 */
public class Storage {

    private String file;

    public Storage(String filePath) {
        this.file = filePath;
    }

    /**
     * Loads the existing tasks into the abstracted database class called TaskList
     * If none exist, return an empty ArrayList
     *
     * @return an ArrayList of Tasks or null if such file does not exist
     * @throws RaisinChatException if file is corrupted or does not follow expected format for the database
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
                return null; // nothing to read yet
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
     * Saves the current tasks into a text file
     *
     * @param task List of current tasks before program exits
     * @return if task list was saved or not, false if and only if it fails to find the file and overwrite it
     */
    public boolean save(TaskList task) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (int i = 0; i < task.size(); i++) {
                bw.write(task.getTasks(i).fullString());
                if (i < task.size() - 1) {
                    bw.newLine();
                }
            }
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
