package raisinchat.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Abstraction of a Deadline task
 */
public class Deadline extends Task {
    protected LocalDateTime deadlineBy;

    /**
     * Creates the Deadline Task object
     *
     * @param name     of task
     * @param haveDone false as default
     * @param doBy     deadline of the task
     */
    public Deadline(String name, boolean haveDone, LocalDateTime doBy) {
        super(name, haveDone);
        this.deadlineBy = doBy;
    }

    /**
     * Support method to update Deadline tasks
     *
     * @param newBy The new deadline to update this specific Deadline object
     */
    public void setBy(LocalDateTime newBy) {
        assert newBy != null;
        this.deadlineBy = newBy;
    }

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
