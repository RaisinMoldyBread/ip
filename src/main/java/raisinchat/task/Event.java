package raisinchat.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Abstraction of an Event task
 */
public class Event extends Task {

    protected LocalDateTime startBy;
    protected LocalDateTime endBy;

    /**
     * Creates an event task.
     *
     * @param name     task name
     * @param haveDone whether the task is completed
     * @param start    start date-time of the event
     * @param end      end date-time of the event
     */
    public Event(String name, boolean haveDone, LocalDateTime start, LocalDateTime end) {
        super(name, haveDone);
        this.startBy = start;
        this.endBy = end;
    }

    /**
     * Returns the storage format used for persistence.
     *
     * @return full storage string for this task
     */
    public String getFullString() {
        return String.format("E | %s | %s -> %s",
                super.toString(),
                this.startBy,
                this.endBy);
    }

    /**
     * Returns the start date-time.
     *
     * @return event start date-time
     */
    public LocalDateTime getFrom() {
        return startBy;
    }

    /**
     * Updates the start date-time.
     *
     * @param from new start date-time
     */
    public void setFrom(LocalDateTime from) {
        assert from != null;
        this.startBy = from;
    }

    /**
     * Returns the end date-time.
     *
     * @return event end date-time
     */
    public LocalDateTime getTo() {
        return endBy;
    }

    /**
     * Updates the end date-time.
     *
     * @param to new end date-time
     */
    public void setTo(LocalDateTime to) {
        assert to != null;
        this.endBy = to;
    }

    @Override
    public String toString() {
        return String.format("E | %s | %s -> %s",
                super.toString(),
                this.startBy.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a", Locale.ENGLISH)),
                this.endBy.format(DateTimeFormatter.ofPattern("MMM d yyyy hh:mm a", Locale.ENGLISH)));
    }
}
