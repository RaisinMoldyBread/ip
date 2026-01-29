package raisinchat.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDateTime startBy;
    protected LocalDateTime endBy;

    public Event(String name, boolean haveDone, LocalDateTime start, LocalDateTime end) {
        super(name, haveDone);
        this.startBy = start;
        this.endBy = end;
    }

    public String fullString() {
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
