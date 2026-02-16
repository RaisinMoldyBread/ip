package raisinchat.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    protected LocalDateTime deadlineBy;

    /**
     * Creates a deadline task.
     *
     * @param name     task name
     * @param haveDone whether the task is completed
     * @param doBy     deadline date-time
     */
    public Deadline(String name, boolean haveDone, LocalDateTime doBy) {
        super(name, haveDone);
        this.deadlineBy = doBy;
    }

    /**
     * Updates the deadline date-time.
     *
     * @param newBy new deadline date-time
     */
    public void setBy(LocalDateTime newBy) {
        assert newBy != null;
        this.deadlineBy = newBy;
    }

    /**
     * Returns the storage format used for persistence.
     *
     * @return full storage string for this task
     */
    @Override
    public String getFullString() {
        return String.format("D | %s | %s", super.toString(),
                this.deadlineBy);
    }

    @Override
    public String toString() {
        return String.format("D | %s | %s", super.toString(),
                this.deadlineBy.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a")));
    }
}
