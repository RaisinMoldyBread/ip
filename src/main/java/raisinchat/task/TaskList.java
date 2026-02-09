package raisinchat.task;

import java.util.ArrayList;

/**
 * Abstraction of a database of Tasks that is currently used by the program
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Creates the TaskList object based on Storage class loading
     *
     * @param existingList of task if they exist, if not create a new one
     */
    public TaskList(ArrayList<Task> existingList) {
        assert existingList != null : "existingList must not be null";
        this.taskList = existingList;
    }

    /**
     * Creates the TaskList object on empty load
     *
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public void addTask(Task task) {
        assert task != null : "Cannot add null Task";
        this.taskList.add(task);
    }

    public void deleteTask(Task task) {
        int initialSize = this.taskList.size();
        this.taskList.remove(task);
        assert this.size() == initialSize - 1 : "Task list size did not decrease by 1";
    }

    public Task getTasks(int taskIndex) {
        return this.taskList.get(taskIndex);
    }

    public int size() {
        return this.taskList.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Here are the list of task right now:\n");
        for (int i = 0; i < taskList.size(); i++) {
            sb.append(i + 1)
                    .append(". ")
                    .append(taskList.get(i).toString())
                    .append("\n");
        }
        return sb.toString();
    }
}
