package raisinchat.task;

import java.util.ArrayList;

/**
 * Maintains the in-memory list of tasks used by the application.
 */
public class TaskList {
    private final ArrayList<Task> taskList;

    /**
     * Creates a task list from an existing collection.
     *
     * @param existingList list of tasks to initialize with
     */
    public TaskList(ArrayList<Task> existingList) {
        assert existingList != null : "existingList must not be null";
        this.taskList = existingList;
    }

    /**
     * Creates an empty task list.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Adds a task to the list.
     *
     * @param task task to add
     */
    public void addTask(Task task) {
        assert task != null : "Cannot add null Task";
        this.taskList.add(task);
    }

    /**
     * Removes a task from the list.
     *
     * @param task task to remove
     */
    public void deleteTask(Task task) {
        int initialSize = this.taskList.size();
        this.taskList.remove(task);
        assert this.size() == initialSize - 1 : "Task list size did not decrease by 1";
    }

    /**
     * Returns the task at the given index.
     *
     * @param taskIndex zero-based index
     * @return task at the given index
     */
    public Task getTask(int taskIndex) {
        return this.taskList.get(taskIndex);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return task count
     */
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
