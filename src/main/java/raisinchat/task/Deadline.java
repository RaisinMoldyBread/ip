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
     * @param name of task
     * @param haveDone false as default
     * @param doBy deadline of the task
     */
    public Deadline(String name, boolean haveDone, LocalDateTime doBy) {
        super(name, haveDone);
        this.deadlineBy = doBy;
    }

    @Override
    public String fullString() {
        return String.format("D | %s | %s", super.toString(),
                this.deadlineBy);
    }

    @Override
    public String toString() {
        return String.format("D | %s | %s", super.toString(),
                this.deadlineBy.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a")));
    }
}
