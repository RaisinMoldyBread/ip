public class Deadline extends Task {
    protected String deadlineBy;

    public Deadline(String name, String doBy) {
        super(name);
        this.deadlineBy = doBy;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.deadlineBy);
    }
}
