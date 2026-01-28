public class Deadline extends Task {
    protected String deadlineBy;

    public Deadline(String name, boolean haveDone, String doBy) {
        super(name, haveDone);
        this.deadlineBy = doBy;
    }

    @Override
    public String toString() {
        return String.format("D | %s | %s", super.toString(), this.deadlineBy);
    }
}
