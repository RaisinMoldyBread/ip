package raisinchat.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Abstraction of an Event task
 */
public class Event extends Task {

    protected LocalDateTime startBy;
    protected LocalDateTime endBy;

    /**
     * Creates the Deadline Task object
     *
     * @param name     of task
     * @param haveDone false as default
     * @param start    starting datetime of the task
     * @param end      ending datetime of the task
     */
    public Event(String name, boolean haveDone, LocalDateTime start, LocalDateTime end) {
        super(name, haveDone);
        this.startBy = start;
        this.endBy = end;
    }

    /**
     * Prints the full format of the tasks for saving so that loading from database will not hit any parsing issue
     *
     * @return full string and format of task and the datetimes
     */
    public String getFullString() {
        return String.format("E | %s | %s -> %s",
                super.toString(),
                this.startBy,
                this.endBy);
    }

    @Override
    public String toString() {
        return String.format("E | %s | %s -> %s",
                super.toString(),
                this.startBy.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a")),
                this.endBy.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a")));
    }
}
