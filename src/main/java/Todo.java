public class Todo extends Task {

    public Todo(String name, boolean haveDone) {
        super(name, haveDone);
    }

    @Override
    public String toString() {
        return String.format("T | %s", super.toString());
    }
}
