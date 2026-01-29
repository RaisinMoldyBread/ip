import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDateTime deadlineBy;

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
