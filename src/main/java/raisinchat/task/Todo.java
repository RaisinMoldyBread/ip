package raisinchat.task;

/**
 * Represents a to-do task without date constraints.
 */
public class Todo extends Task {

    /**
     * Creates a to-do task.
     *
     * @param name     task name
     * @param haveDone whether the task is completed
     */
    public Todo(String name, boolean haveDone) {
        super(name, haveDone);
    }

    /**
     * Returns the storage format used for persistence.
     *
     * @return full storage string for this task
     */
    @Override
    public String getFullString() {
        return String.format("T | %s", super.toString());
    }

    @Override
    public String toString() {
        return String.format("T | %s", super.toString());
    }
}
