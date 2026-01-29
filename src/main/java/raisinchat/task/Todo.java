package raisinchat.task;

public class Todo extends Task {

    public Todo(String name, boolean haveDone) {
        super(name, haveDone);
    }

    public String fullString() {
        return String.format("T | %s", super.toString());
    }

    @Override
    public String toString() {
        return String.format("T | %s", super.toString());
    }
}
